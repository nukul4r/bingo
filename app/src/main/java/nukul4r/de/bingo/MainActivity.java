package nukul4r.de.bingo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MainActivity extends Activity {
    public static final String PREFS = "prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        final SharedPreferences settings = getSharedPreferences(PREFS, 0);
        List<String> list = new ArrayList<String>();

        if (settings.contains("label0")) {
            for (int i = 0; i < 9; i++) {
                list.add(settings.getString("label" + Integer.toString(i), ""));
            }
        } else {
            List<String> newList = getNewList();
            for (int i = 0; i < 9; i++) {
                settings.edit().putString("label" + Integer.toString(i), newList.get(i)).commit();
                list.add(newList.get(i));
            }
        }

        final GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new CellAdapter(this, list));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_new) {
            reset();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void reset() {
        final SharedPreferences settings = getSharedPreferences(PREFS, 0);
        settings.edit().clear().commit();
        init();
    }

    public List<String> getNewList() {
        List<String> list = Arrays.asList("Über weite Strecken ohne Impulse", "Abseits ist, wenn der Schiedsrichter pfeift.", "Am Ende verdient", "An den Innenpfosten", "An die Latte", "Anders als im letzten Spiel", "Auf dem Papier haben sie die stärkere Mannschaft.", "Auftakt nach Maß", "Aus einer Standardsituation heraus", "Beweglich wie kaum ein anderer", "Da bleibt ein großes Fragezeichen", "Da bleibt einiges zu klären", "Da brennt nichts an", "Da dürfte eigentlich nichts mehr anbrennen", "Da fehlte nicht viel", "Da fehlten nur Millimeter", "Da geht nichts mehr", "Da geht noch was", "Da gibt es Dinge zu verbessern", "Da haben wir wieder die Situation", "Da hat nicht viel gefehlt", "Da leg ich mich fest", "Da sind sie die ersten Pfiffe", "Dafür muss es Gelb geben", "Damit kommen wir nicht weiter", "Das Tor fiel zu einem ungünstigen Zeitpunkt.", "Das Tor ist wie vernagelt", "Das hat er gut gesehen", "Das ist doch überflüssig", "Das ist heute ein Sechs-Punkte-Spiel", "Das ist internationale Härte.", "Das ist seine Entfernung", "Das kann dem Trainer nicht gefallen", "Das kann man pfeifen", "Das konnte keiner voraussehen", "Das müssen sie jetzt nach Hause zittern", "Das muss er pfeifen", "Das muss man überprüfen", "Das muss man einfach mal sagen", "Das war dunkelgelb", "Das war ein Auftakt nach Maß.", "Das war psychologisch wichtig", "Den muss er machen", "Der Ball ist rund", "Der Druck ist zu groß", "Der Gefoulte sollte nie selbst schießen.", "Der Gegner hat uns überrascht", "Der Pokal hat seine eigenen Gesetze", "Der Spieler ist jeden Cent wert", "Der kann auch Tore schießen", "Der kann aus der Entfernung schießen", "Der weiß wo der Ball hin muss", "Die Abwehr bleibt das Sorgenkind", "Die Butter vom Brot nehmen", "Die Chance lässt er sich nicht entgehen", "Die Fehler sind erkannt", "Die Luft ist raus", "Die Mannschaft ist nicht wiederzuerkennen", "Die Spieler stehen in der Pflicht", "Die Tagesform wird entscheidend sein.", "Die müssen jetzt Gas geben", "Die reguläre Spielzeit ist zu Ende", "Die stark verjüngte Elf", "Ein Tor würde dem Spiel gut tun", "Ein Totalausfall", "Ein paar Kohlen nachlegen", "Eine gute Flanke aber leider stand niemand in der Mitte", "Er hat nicht mehr die Geschwindigkeit von früher.", "Es fehlen die zwingenden Aktionen", "Es gab viele Diskussionen", "Es gelingt aber nicht alles", "Findet keine Anspielstation", "Fußball ist reine Kopfsache", "Gefährlich!", "Geht voll in Ordnung", "Genau wie im letzten Spiel", "Hat sich bemüht", "Hatte ein paar gute Situationen", "Hauteng gedeckt", "Heute geht's um die Wurst", "Hinten brennt es lichterloh.", "Hinten muss die null stehen", "Ist er überfordert?", "Jetzt haben Sie den Salat", "Jetzt muss jeder 110 Prozent geben", "Klare Abseitsposition", "Klare Fehlentscheidung", "Klasse gemacht", "Knapp vorbei ist auch daneben.", "Korrekt gesehen vom Unparteiischen", "Korrekte Entscheidung", "Laufstark wie immer", "Locker verwandelt", "Mehr Licht als Schatten", "Mit viel Übersicht", "Mit viel Fingerspitzengefühl", "Na es geht doch", "Nach den Ursachen suchen", "Noch nicht zu ihrem Spiel gefunden", "Sauber vom Ball getrennt", "Schade", "Schiedsrichter läßt Vorteil gelten", "Schwer auszurechnen", "Sie lassen den Gegner wieder spielen", "Sie müssen die Räume eng machen", "Sie müssen mehr über außen kommen", "Sie spielen hier auf Sieg.", "Sie wollen ein frühes Tor erzielen", "Sieht die rote Karte", "Skandalumwittert", "Sträflich alleingelassen", "Sucht den Abschluss", "Um es noch mal zu sagen", "Unerreichbar für Torwart", "Völlig von der Rolle", "Von den Beinen geholt", "Was für ein Hammer!", "Wenige Sekunden vor dem Abpfiff", "Wie ausgewechselt", "Wir müssen noch einiges abstellen", "Wir waren gewarnt", "Zu Recht macht er das Tor", "Zu wenig Laufbereitschaft", "Zurück ins Studio");
        List<String> shuffled = new ArrayList<String>(list);
        Collections.shuffle(shuffled);
        return shuffled.subList(0, 9);
    }

    public void weHaveABingo() {
        final SharedPreferences settings = getSharedPreferences(PREFS, 0);
        boolean zero = settings.getBoolean("0", false);
        boolean one = settings.getBoolean("1", false);
        boolean two = settings.getBoolean("2", false);
        boolean three = settings.getBoolean("3", false);
        boolean four = settings.getBoolean("4", false);
        boolean five = settings.getBoolean("5", false);
        boolean six = settings.getBoolean("6", false);
        boolean seven = settings.getBoolean("7", false);
        boolean eight = settings.getBoolean("8", false);

        if ((zero && one && two)
                || (three && four && five)
                || (six && seven && eight)
                || (zero && three && six)
                || (one && four && seven)
                || (two && five && eight)
                || (zero && four && eight)
                || (two && four && six)) {

            AlertDialog bingo = new AlertDialog.Builder(this).setTitle("We have a Bingo!").show();

            bingo.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    reset();
                }
            });
        }
    }
}
