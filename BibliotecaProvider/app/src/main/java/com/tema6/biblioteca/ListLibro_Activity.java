package com.tema6.biblioteca;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

import com.tema6.biblioteca.BibliotecaProvider.LibrosColumns;

public class ListLibro_Activity extends ListActivity implements
		LoaderManager.LoaderCallbacks<Cursor> {

	// This is the Adapter being used to display the list's data
	private SimpleCursorAdapter mAdapter;
	private static final Uri librosUri = BibliotecaProvider.CONTENT_URI;
	private ProgressBar progressBar;

	// Contruimos el array de columnas que queremos recoger
	private static final String[] PROJECTION = new String[] {
			BibliotecaProvider.LibrosColumns._ID,
			BibliotecaProvider.LibrosColumns.KEY_ISBN,
			BibliotecaProvider.LibrosColumns.KEY_AUTOR,
			BibliotecaProvider.LibrosColumns.KEY_TITULO,
			BibliotecaProvider.LibrosColumns.KEY_IDIOMA,

	};
	private static final int LOADER_ID = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Creamos la barra de progreso
		progressBar = new ProgressBar(this);
		progressBar.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		progressBar.setIndeterminate(true);
		getListView().setEmptyView(progressBar);
		// Muestra esta vista mientras el adaptador esté vacío, como
		// inicializamos el Adapter a null, se mostrará la barra de progreso

		// Se añade la barra de progreso al grupo de vista principal
		ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
		root.addView(progressBar);

		// Creamos el adaptador con datos= null y se mostrará la barra de
		// progreso hasta
		// que se cargan los datos en onLoadFinished();

		int[] camposTexto = { R.id.txtId, R.id.txtIsbn, R.id.txtAutor,
				R.id.txtTitulo, R.id.txtIdioma };
		String[] columnas = { LibrosColumns._ID, LibrosColumns.KEY_ISBN,
				LibrosColumns.KEY_AUTOR, LibrosColumns.KEY_TITULO,
				LibrosColumns.KEY_IDIOMA };

		// VERSIÓN 4.1
		mAdapter = new SimpleCursorAdapter(this, R.layout.ver_libro, null,
				columnas, camposTexto, 0);

		setListAdapter(mAdapter);

		// Inicializa el cargador. Si hubiera alguno, se realiza la reconexión
		// con el cargador existente

		getLoaderManager().initLoader(LOADER_ID, null, this);

	}

	// Called when a new Loader needs to be created
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {

		// Now create and return a CursorLoader that will take care of
		// creating a Cursor for the data being displayed.
		return new CursorLoader(this, librosUri, PROJECTION, null, null, null);
	}

	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		switch (loader.getId()) {
		case LOADER_ID:
			// Carga los datos en el adaptador si hubiera un cursor anterior se
			// cerraría
			mAdapter.swapCursor(data);
			// progressBar.invalidate();

		}
	}

	// Called when a previously created loader is reset, making the data
	// unavailable
	public void onLoaderReset(Loader<Cursor> loader) {
		// This is called when the last Cursor provided to onLoadFinished()
		// above is about to be closed. We need to make sure we are no
		// longer using it.
		mAdapter.swapCursor(null);
	}
}
