package metodos;

import entidades.Pan;
import java.util.List;

public interface AlgoritmoSort {
    void sort(List<Pan> listaPanes, int inicio, int fin, boolean ascendente);
}
