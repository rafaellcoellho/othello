package logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Eventos {

    Map<String, List<Observador>> observadores = new HashMap<>();

    public Eventos(String... eventos) {
        for (String evento : eventos) {
            this.observadores.put(evento, new ArrayList<>());
        }
    }

    public void adicionarObservador(String evento, Observador observador) {
        List<Observador> componentes = observadores.get(evento);
        componentes.add(observador);
    }

    public void removerObservador(String evento, Observador observador) {
        List<Observador> componentes = observadores.get(evento);
        componentes.remove(observador);
    }

    public void notificarObservadores(String evento, Logica jogo) {
        List<Observador> componentes = observadores.get(evento);
        for (Observador observador : componentes) {
            observador.reagir(evento, jogo);
        }
    }
}
