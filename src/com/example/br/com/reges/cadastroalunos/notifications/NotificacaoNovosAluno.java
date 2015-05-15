package com.example.br.com.reges.cadastroalunos.notifications;


import com.example.br.com.reges.cadastroalunos.ListaActivity;
import com.example.br.com.reges.cadastroalunos.R;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

@SuppressLint("NewApi")
public class NotificacaoNovosAluno {
	
	IntentService ctx;
	
	public NotificacaoNovosAluno(IntentService ctx){
		this.ctx = ctx;
	}
	
	@SuppressLint("NewApi")
	public void criarNotificacao(Integer numeoDeNovosAlunos){
		
		Intent intent = new Intent(ctx, ListaActivity.class);
		PendingIntent pIntent = PendingIntent.getActivity(ctx, 0, intent, 0);
		
		Notification notification = new Notification.Builder(ctx)
			.setContentTitle("Novos alunos foram importados do servidor")
			.setContentText("Numero de novos alunos: " + numeoDeNovosAlunos)
			.setSmallIcon(R.drawable.ic_no_image)
			.setContentIntent(pIntent)
			.setAutoCancel(true)
			.addAction(R.drawable.ic_no_image, "Mais informações", pIntent).build();
		
		NotificationManager notificationManager = 
				(NotificationManager) ctx.getSystemService(IntentService.NOTIFICATION_SERVICE);
		
		notificationManager.notify(0,notification);
		
	}

}
