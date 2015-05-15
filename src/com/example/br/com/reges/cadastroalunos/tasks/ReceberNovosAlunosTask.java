package com.example.br.com.reges.cadastroalunos.tasks;

import com.example.br.com.reges.cadastroalunos.communication.WebClient;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class ReceberNovosAlunosTask extends AsyncTask<Object, Object, String>{

	private Context ctx;
	ProgressDialog progressDialog;
	
	public ReceberNovosAlunosTask(Context ctx){
		this.ctx = ctx;
	}
	
	@Override
	protected void onPreExecute() {
		progressDialog = ProgressDialog.show(ctx, "Aguarde...", "Recuperando novos alunos do servidor.");
	}
	
	@Override
	protected String doInBackground(Object... params) {
		WebClient wClient = new WebClient("http://10.100.149.153:8080/alunos/30");
		String jsonDeNovosAlunos = wClient.recuperaNovosAlunos();
		
		return jsonDeNovosAlunos;
	}
	
	@Override
	protected void onPostExecute(String result) {
		progressDialog.dismiss();
		Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
	}

}
