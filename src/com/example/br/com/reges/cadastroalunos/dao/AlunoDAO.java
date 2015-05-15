package com.example.br.com.reges.cadastroalunos.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.br.com.reges.cadastroalunos.model.Aluno;

public class AlunoDAO extends SQLiteOpenHelper {

	private static final int VERSAO = 2;
	private static final String DATABASE = "CadastroDeAlunos";
	private static final String TABELA = "Alunos";

	public AlunoDAO(Context context) {
		super(context, DATABASE, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = " CREATE TABLE " + TABELA + "("
				+ " id INTEGER PRIMARY KEY," + " nome TEXT UNIQUE NOT NULL,"
				+ " endereco TEXT, " + " telefone TEXT, " + " nota REAL "
				+ " );";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "DROP TABLE IF EXISTS " + TABELA;
		db.execSQL(sql);
		onCreate(db);

	}

	public void insere(Aluno alunoParaSalvarNoDatabase) {

		ContentValues values = new ContentValues();
		values.put("nome", alunoParaSalvarNoDatabase.getNome());
		values.put("endereco", alunoParaSalvarNoDatabase.getEndereco());
		values.put("telefone", alunoParaSalvarNoDatabase.getTelefone());
		values.put("nota", alunoParaSalvarNoDatabase.getNota());

		getWritableDatabase().insert(TABELA, null, values);

	}

	public void update(Aluno alunoParaSalvarNoDatabase) {

		ContentValues values = new ContentValues();
		values.put("nome", alunoParaSalvarNoDatabase.getNome());
		values.put("endereco", alunoParaSalvarNoDatabase.getEndereco());
		values.put("telefone", alunoParaSalvarNoDatabase.getTelefone());
		values.put("nota", alunoParaSalvarNoDatabase.getNota());

		String[] args = { alunoParaSalvarNoDatabase.getId().toString() };
		getWritableDatabase().update(TABELA, values, "id=?", args);

	}

	public List<Aluno> getLista() {
		String sql = " SELECT * FROM " + TABELA + ";";
		Cursor c = getReadableDatabase().rawQuery(sql, null);

		List<Aluno> alunos = new ArrayList<Aluno>();

		while (c.moveToNext()) {
			Aluno aluno = new Aluno();

			aluno.setId(c.getInt(c.getColumnIndex("id")));
			aluno.setNome(c.getString(c.getColumnIndex("nome")));
			aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
			aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
			aluno.setNota(c.getFloat(c.getColumnIndex("nota")));

			alunos.add(aluno);
		}

		return alunos;
	}

	public void deletar(Aluno aluno) {
		String[] args = { aluno.getId().toString() };
		getWritableDatabase().delete(TABELA, "id=?", args);
	}

	public boolean ehTelefoneDeAluno(String telefone) {
		String sql  = " SELECT id FROM " + TABELA + " WHERE telefone = ? ";
		String[] selectionArgs = { telefone };
		Cursor cursor = getReadableDatabase().rawQuery(sql, selectionArgs );
		boolean exiteAluno = cursor.moveToFirst();
		
		return exiteAluno;
	}

	public boolean verificaTelefone(String telefoneDoSMS) {
		
		String sql = " SELECT id FROM " + TABELA + " WHERE telefone = ? ";
		
		String[] arg = {telefoneDoSMS};
		Cursor cursor = getReadableDatabase().rawQuery(sql, arg );
		boolean telefoneExiste = cursor.moveToFirst();
		
		
		return telefoneExiste;
	}

	public int getUltimoIdAluno() {
		String sql = " SELECT max(id) FROM " + TABELA ;
		
		Cursor cursor = getReadableDatabase().rawQuery(sql, null );
		if(cursor.moveToNext()){
			return cursor.getInt(0);
		}else {
			return 0;
		}
	}

	public int getUltimoAlunoInserido() {
		
		String sql = " SELECT max(id) FROM " + TABELA;
		Cursor cursor = getReadableDatabase().rawQuery(sql, null);

		if (cursor.moveToNext()){
			return cursor.getInt(0);
		} else {
			return 0;
		}
	}

}
