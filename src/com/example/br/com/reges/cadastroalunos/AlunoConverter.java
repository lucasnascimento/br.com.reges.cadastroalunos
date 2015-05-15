package com.example.br.com.reges.cadastroalunos;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.br.com.reges.cadastroalunos.model.Aluno;

public class AlunoConverter {

	public List<Aluno> getAlunosFromJson(String json) {
		try {
			JSONObject jsonObj = new JSONObject(json);
			JSONArray alunos = jsonObj.getJSONArray("alunos");
			List<Aluno> alunosList = new ArrayList<Aluno>();
			for (int i = 0; i < alunos.length(); i++) {
				JSONObject a = alunos.getJSONObject(i);
				Aluno aluno = new Aluno();
				aluno.setId(a.getInt("id"));
				aluno.setNome(a.getString("nome"));
				aluno.setEndereco(a.getString("endereco"));
				aluno.setTelefone(a.getString("telefone"));
				aluno.setNota(Float.parseFloat(a.getDouble("nota") + ""));
				alunosList.add(aluno);
			}
			return alunosList;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

}
