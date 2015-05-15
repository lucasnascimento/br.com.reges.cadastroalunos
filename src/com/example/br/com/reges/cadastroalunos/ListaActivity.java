package com.example.br.com.reges.cadastroalunos;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.br.com.reges.cadastroalunos.dao.AlunoDAO;
import com.example.br.com.reges.cadastroalunos.model.Aluno;
import com.example.br.com.reges.cadastroalunos.service.RecuperarNovosAlunosService;
import com.example.br.com.reges.cadastroalunos.service.RecuperarNovosAlunosService_;
import com.example.br.com.reges.cadastroalunos.tasks.ReceberNovosAlunosTask;

@SuppressLint("NewApi")
public class ListaActivity extends ActionBarActivity {

	Aluno aluno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);

		ListView listView = (ListView) findViewById(R.id.lista);

		registerForContextMenu(listView);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int position, long id) {

				Intent irParaFormularioAlterando = new Intent(
						ListaActivity.this, FormularioActivity.class);

				Aluno alunoParaAlterar = (Aluno) adapter
						.getItemAtPosition(position);

				irParaFormularioAlterando.putExtra("alunoParaAlterar",
						alunoParaAlterar);

				startActivity(irParaFormularioAlterando);
			}
		});

		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {

				aluno = (Aluno) adapter.getItemAtPosition(position);

				return false;
			}
		});

		

		Intent irParaServico = new Intent(this,	RecuperarNovosAlunosService.class);
		startService(irParaServico);
		
		Log.i("cadastro", "startouService");

	}

	@Override
	protected void onResume() {

		super.onResume();

		atualizaListaDeAlunos();
	}

	private void atualizaListaDeAlunos() {
		ListView listView = (ListView) findViewById(R.id.lista);
		AlunoDAO alunoDAO = new AlunoDAO(this);
		List<Aluno> alunos = alunoDAO.getLista();

		ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this,
				android.R.layout.simple_list_item_1, alunos);

		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.novo:
			Intent intent = new Intent(this, FormularioActivity.class);
			startActivity(intent);
			break;
		case R.id.receber:
			new ReceberNovosAlunosTask(this).execute();
			break;

		case R.id.notificar:

			break;

		// case R.id.receber:
		// new ReceberNovosAlunosTask_(this).execute();
		// break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		MenuItem menuDeletar = menu.add("Deletar");

		menuDeletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDAO alunoDAO = new AlunoDAO(ListaActivity.this);
				alunoDAO.deletar(aluno);
				alunoDAO.close();
				atualizaListaDeAlunos();
				return false;
			}
		});

		MenuItem menuTelefonar = menu.add("Telefonar");

		menuTelefonar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {

				Intent irParaTelaDeDiscagem = new Intent(Intent.ACTION_CALL);

				irParaTelaDeDiscagem.setData(Uri.parse("tel:"
						+ aluno.getTelefone()));

				startActivity(irParaTelaDeDiscagem);

				return false;
			}
		});

		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
	}
}
