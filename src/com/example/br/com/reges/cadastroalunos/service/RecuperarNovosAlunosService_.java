package com.example.br.com.reges.cadastroalunos.service;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;

import com.example.br.com.reges.cadastroalunos.AlunoConverter;
import com.example.br.com.reges.cadastroalunos.communication.WebClient;
import com.example.br.com.reges.cadastroalunos.dao.AlunoDAO;
import com.example.br.com.reges.cadastroalunos.model.Aluno;
import com.example.br.com.reges.cadastroalunos.notifications.NotificacaoNovosAlunos_;

public class RecuperarNovosAlunosService_ extends IntentService {


	public RecuperarNovosAlunosService_() {
		super("RecuperarNovosAlunosService");
	}
	
	public RecuperarNovosAlunosService_(String name) {
		super(name);
	}


	@SuppressLint("NewApi")
	@Override
	protected void onHandleIntent(Intent intent) {
		
		AlunoDAO alunoDAO = new AlunoDAO(this);
		int ultimoId = alunoDAO.getUltimoIdAluno();
		
		WebClient wClient = new WebClient("http://10.100.149.153:8080/alunos/"+ultimoId);
		String jsonDeNovosAlunos = wClient.recuperaNovosAlunos();
		
		List<Aluno> novosAlunosDoServidor = new AlunoConverter().getAlunosFromJson(jsonDeNovosAlunos);
		for (Aluno novoAluno : novosAlunosDoServidor){
			alunoDAO.insere(novoAluno);
		}
		
		new NotificacaoNovosAlunos_(this).criarNoficacao(novosAlunosDoServidor.size());
		
	}



}
