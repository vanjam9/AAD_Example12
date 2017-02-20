package rs.aleph.android.example12.activities;


import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import rs.aleph.android.example12.R;


/**
 * Created by milossimic on 10/22/16.
 * AsyncTask klasa prima tri parametra prilikom specijalizacije
 * Korisnici sami definisu tip u zavisnosti od posla koji zele da obave.
 *
 * Prvi argument predstavlja ulazne parametre, ono so zelimo
 * da posaljemo zadatku. Recimo ime fajla koji zelimo da skinemo
 *
 * Drugi argument je indikator kako ce se meriti progres. Koliko je posla
 * zavrseno i koliko je opsla ostalo.
 *
 * Treci parametar je povratna vrednost, tj sta ce metoda doInBackground
 * vratiti kao poratnu vrednost metodi onPostExecute
 */
public class SimpleSyncTask extends AsyncTask<Void, Void, Void>{

    private MainActivity activity;

    public SimpleSyncTask(MainActivity activity) {
        this.activity = activity;

    }

    /**
     * Metoda se poziva pre samog starta pozadinskog zadatka
     * Sve pripreme odraditi u ovoj metodi, ako ih ima.
     */
    @Override
    protected void onPreExecute() {
    }

    /**
     * Posao koji se odvija u pozadini, ne blokira glavnu nit aplikacije.
     * Sav posao koji dugo traje izvrsavati unutar ove metode.
     */
    @Override
    protected Void doInBackground(Void... params) {
        try {
            //simulacija posla koji se obavlja u pozadini i traje duze vreme
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }
    private void fillProduct (){
        final List<String> listaImena = JeloProvajder.getJelaNames();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(activity, R.layout.list_item, listaImena);
        ListView listView = (ListView) activity.findViewById(R.id.listaJela);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(activity,SecondActivity.class);
                intent.putExtra("",i);
                activity.startActivity(intent);
            }
        });
    }




    /**
     *
     *
     * Kada se posao koji se odvija u pozadini zavrsi, poziva se ova metoda
     * Ako je potrebno osloboditi resurse ili obrisati elemente koji vise ne trebaju.
     */
    @Override
    protected void onPostExecute(Void products) {
        Toast.makeText(activity, "Sync done", Toast.LENGTH_SHORT).show();
        fillProduct();
    }
}
