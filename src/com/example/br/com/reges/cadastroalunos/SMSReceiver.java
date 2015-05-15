package com.example.br.com.reges.cadastroalunos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;

import com.example.br.com.reges.cadastroalunos.dao.AlunoDAO;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		Object[] mensgens = (Object[]) intent.getExtras().get("pdus");
		byte[] mensagem = (byte[]) mensgens[0];
		
		SmsMessage sms = SmsMessage.createFromPdu(mensagem);
		
		String telefone = sms.getOriginatingAddress();
		
		AlunoDAO alunoDAO = new AlunoDAO(context);
		
		boolean smsDeAluno = alunoDAO.ehTelefoneDeAluno(telefone);
		
		if (smsDeAluno){
			MediaPlayer musica = MediaPlayer.create(context, R.raw.sms);
			musica.start();
		}

		
	}

}
