package com.example.br.com.reges.cadastroalunos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.br.com.reges.cadastroalunos.dao.AlunoDAO;
import com.example.br.com.reges.cadastroalunos.helpers.FormularioHelper;
import com.example.br.com.reges.cadastroalunos.model.Aluno;

public class FormularioActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_formulario);
																		   			
		final Aluno alunoParaAlterar = (Aluno) getIntent().getSerializableExtra("alunoParaAlterar");
				
		Button bntSalvar = (Button) findViewById(R.id.btnsalvar);
		final FormularioHelper helper = new FormularioHelper(this);
		
		if (alunoParaAlterar != null){
			//Estou no formulario para Alterar um aluno
			helper.carregaAlunoNoFormulario(alunoParaAlterar);
			bntSalvar.setText("Alterar");
		}
		
		bntSalvar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Aluno alunoParaSalvarNoDatabase = helper.recuperaAluno();
				AlunoDAO alunoDAO = new AlunoDAO(FormularioActivity.this);
				
				if (alunoParaAlterar != null){
					alunoParaSalvarNoDatabase.setId(alunoParaAlterar.getId());
					alunoDAO.update(alunoParaSalvarNoDatabase);
				}else{
					alunoDAO.insere(alunoParaSalvarNoDatabase);
				}
				alunoDAO.close();
				finish();
			}
		});
		
	}

}
