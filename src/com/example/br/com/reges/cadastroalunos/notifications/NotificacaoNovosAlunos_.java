package com.example.br.com.reges.cadastroalunos.notifications;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.example.br.com.reges.cadastroalunos.ListaActivity;
import com.example.br.com.reges.cadastroalunos.R;

public class NotificacaoNovosAlunos_ {
	
	IntentService ctx;
	
	public NotificacaoNovosAlunos_(IntentService ctx){
		this.ctx = ctx;
	}
	
	@SuppressLint("NewApi")
	public void criarNoficacao(Integer numeroDeNovosAlunos) {
		
		Intent irParaLista = new Intent(ctx, ListaActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, irParaLista, 0);
		
		Notification n  = new Notification.Builder(ctx)
		        .setContentTitle("Novos alunos foram importados do servidor")
		        .setContentText("Numero de novos alunos: "+ numeroDeNovosAlunos)
		        .setSmallIcon(R.drawable.ic_no_image)
		        .setContentIntent(pIntent)
		        .setAutoCancel(true)
		        .addAction(R.drawable.ic_no_image, "Mais Informações", pIntent).build();


		NotificationManager notificationManager = 
				  (NotificationManager) ctx.getSystemService(IntentService.NOTIFICATION_SERVICE);

		notificationManager.notify(0, n);
	}

}
