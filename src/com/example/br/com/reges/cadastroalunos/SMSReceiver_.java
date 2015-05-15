package com.example.br.com.reges.cadastroalunos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;

import com.example.br.com.reges.cadastroalunos.dao.AlunoDAO;

public class SMSReceiver_ extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		Object[] mensagens = (Object[]) intent.getExtras().get("pdus");
		
		byte[] ultimaMensagem = (byte[]) mensagens[0]; //Posição 1
		
		SmsMessage smsRecebido = SmsMessage.createFromPdu(ultimaMensagem);
		
		String telefoneDoSMS = smsRecebido.getOriginatingAddress();
		
		AlunoDAO alunoDAO = new AlunoDAO(context);
		
		boolean ehTelefoneDeAluno = alunoDAO.verificaTelefone(telefoneDoSMS);
		
		if(ehTelefoneDeAluno){
			//Toca musiquinha
			MediaPlayer musica = MediaPlayer.create(context, R.raw.sms);
			musica.start();
		}
	}

}
