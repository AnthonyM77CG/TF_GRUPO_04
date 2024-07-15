package metodos;

import entidades.Pan;
import java.util.List;

public interface AlgoritmoBinary {
    Pan buscarPorNombre(List<Pan> listaPanes, String nombre);
    Pan buscarPorPrecio(List<Pan> listaPanes, double precio);
}
