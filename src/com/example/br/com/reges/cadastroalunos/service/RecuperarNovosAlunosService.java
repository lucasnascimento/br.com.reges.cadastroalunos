package com.example.br.com.reges.cadastroalunos.service;

import java.util.List;

import android.app.IntentService;
import android.content.Intent;

import com.example.br.com.reges.cadastroalunos.AlunoConverter;
import com.example.br.com.reges.cadastroalunos.communication.WebClient;
import com.example.br.com.reges.cadastroalunos.dao.AlunoDAO;
import com.example.br.com.reges.cadastroalunos.model.Aluno;
import com.example.br.com.reges.cadastroalunos.notifications.NotificacaoNovosAluno;

public class RecuperarNovosAlunosService extends IntentService{

	public RecuperarNovosAlunosService(){
		super("MeuServico");
		
	}
	
	public RecuperarNovosAlunosService(String name) {
		super(name);
	}

	
	@Override
	protected void onHandleIntent(Intent intent) {
		AlunoDAO alunoDAO = new AlunoDAO(this);
		int ultimoAlunoInserido  = alunoDAO.getUltimoAlunoInserido();
		
		WebClient wClient = new WebClient("http://192.168.0.141:8080/alunos/" + ultimoAlunoInserido);
		String jsonDeNovosAlunos = wClient.recuperaNovosAlunos();
		
		AlunoConverter alunoConverter = new AlunoConverter();
		List<Aluno> novosAlunosDoServidor = alunoConverter.getAlunosFromJson(jsonDeNovosAlunos);
		
		for( Aluno novoAluno : novosAlunosDoServidor){
			alunoDAO.insere(novoAluno);	
		}
		
		new NotificacaoNovosAluno(this).criarNotificacao(novosAlunosDoServidor.size());
		
		
	}

}
