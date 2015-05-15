package com.example.br.com.reges.cadastroalunos.helpers;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RatingBar;

import com.example.br.com.reges.cadastroalunos.R;
import com.example.br.com.reges.cadastroalunos.model.Aluno;

public class FormularioHelper {

	EditText txtNome;
	EditText txtEndereco;
	EditText txtTelefone;
	RatingBar txtNota;
	Aluno aluno;

	public FormularioHelper(Activity activity) {
		aluno = new Aluno();
		txtNome = (EditText) activity.findViewById(R.id.nome);
		txtEndereco = (EditText) activity.findViewById(R.id.endereco);
		txtTelefone = (EditText) activity.findViewById(R.id.telefone);
		txtNota = (RatingBar) activity.findViewById(R.id.nota);
	}

	public Aluno recuperaAluno() {
		aluno.setNome(txtNome.getText().toString());
		aluno.setEndereco(txtEndereco.getText().toString());
		aluno.setTelefone(txtTelefone.getText().toString());
		aluno.setNota(txtNota.getRating());

		return aluno;
	}

	public void carregaAlunoNoFormulario(Aluno alunoParaAlterar) {
		txtNome.setText(alunoParaAlterar.getNome());
		txtEndereco.setText(alunoParaAlterar.getEndereco());
		txtTelefone.setText(alunoParaAlterar.getTelefone());

		if (alunoParaAlterar.getNota() != null) {
			txtNota.setRating(alunoParaAlterar.getNota().floatValue());
		}
	}

}
