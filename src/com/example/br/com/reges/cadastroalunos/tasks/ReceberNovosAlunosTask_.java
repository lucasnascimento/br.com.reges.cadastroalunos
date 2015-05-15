package com.example.br.com.reges.cadastroalunos.tasks;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.br.com.reges.cadastroalunos.AlunoConverter;
import com.example.br.com.reges.cadastroalunos.communication.WebClient_;
import com.example.br.com.reges.cadastroalunos.model.Aluno;

public class ReceberNovosAlunosTask_ extends AsyncTask<Object, Object, String>{

	Context ctx;
	ProgressDialog progressDialog;
	
	public ReceberNovosAlunosTask_(Context ctx){
		this.ctx = ctx;
	}
	
	@Override
	protected void onPreExecute() {
		progressDialog = ProgressDialog.show(ctx, "Aguarde...", "Recuperando novos alunos do servidor");
	}
	
	@Override
	protected String doInBackground(Object... params) {
		WebClient_ wClient = new WebClient_("http://10.100.149.153:8080/alunos/19");
		String jsonRetorno = wClient.recuperarNovosAlunos();
		return jsonRetorno;
	}
	
	@Override
	protected void onPostExecute(String result) {
		progressDialog.dismiss();
		Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
		AlunoConverter converter = new AlunoConverter();
		List<Aluno> alunoList = converter.getAlunosFromJson(result);
		Toast.makeText(ctx, alunoList.toString(), Toast.LENGTH_LONG).show();
//		AlunoDAO alunoDAO = new AlunoDAO(ctx);
//		alunoDAO.insereAlunos(alunoList);
	}

}
