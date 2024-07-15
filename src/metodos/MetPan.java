package metodos;

import entidades.Pan;
import java.util.ArrayList;
import java.util.List;

public class MetPan implements AlgoritmoSort, AlgoritmoBinary {

    @Override
    public void sort(List<Pan> listaPanes, int bajo, int alto, boolean ascendente) {
        if (ascendente) {
            quickSortPorNombre(listaPanes, bajo, alto);
        } else {
            quickSortPorNombreDescendente(listaPanes, bajo, alto);
        }
    }

    public void sortPorPrecio(List<Pan> listaPanes, int bajo, int alto, boolean ascendente) {
        if (ascendente) {
            mergeSortPorPrecioAscendente(listaPanes, bajo, alto);
        } else {
            mergeSortPorPrecioDescendente(listaPanes, bajo, alto);
        }
    }

    private void quickSortPorNombre(List<Pan> listaPanes, int bajo, int alto) {
        if (bajo < alto) {
            int pivote = particionarPorNombre(listaPanes, bajo, alto);
            quickSortPorNombre(listaPanes, bajo, pivote - 1);
            quickSortPorNombre(listaPanes, pivote + 1, alto);
        }
    }

    private void quickSortPorNombreDescendente(List<Pan> listaPanes, int bajo, int alto) {
        if (bajo < alto) {
            int pivote = particionarPorNombreDescendente(listaPanes, bajo, alto);
            quickSortPorNombreDescendente(listaPanes, bajo, pivote - 1);
            quickSortPorNombreDescendente(listaPanes, pivote + 1, alto);
        }
    }

    private int particionarPorNombre(List<Pan> listaPanes, int bajo, int alto) {
        Pan pivote = listaPanes.get(alto);
        int i = bajo - 1;
        for (int j = bajo; j < alto; j++) {
            if (listaPanes.get(j).getNombre().compareTo(pivote.getNombre()) < 0) {
                i++;
                intercambiar(listaPanes, i, j);
            }
        }
        intercambiar(listaPanes, i + 1, alto);
        return i + 1;
    }

    private int particionarPorNombreDescendente(List<Pan> listaPanes, int bajo, int alto) {
        Pan pivote = listaPanes.get(alto);
        int i = bajo - 1;
        for (int j = bajo; j < alto; j++) {
            if (listaPanes.get(j).getNombre().compareTo(pivote.getNombre()) > 0) {
                i++;
                intercambiar(listaPanes, i, j);
            }
        }
        intercambiar(listaPanes, i + 1, alto);
        return i + 1;
    }

    private void intercambiar(List<Pan> listaPanes, int i, int j) {
    Pan temp = listaPanes.get(i);
    listaPanes.set(i, listaPanes.get(j));
    listaPanes.set(j, temp);
}

    private void mergeSortPorPrecioAscendente(List<Pan> listaPanes, int bajo, int alto) {
    if (bajo < alto) {
        int medio = (bajo + alto) / 2;
        mergeSortPorPrecioAscendente(listaPanes, bajo, medio);
        mergeSortPorPrecioAscendente(listaPanes, medio + 1, alto);
        mergePorPrecioAscendente(listaPanes, bajo, medio, alto);
    }
}

    private void mergeSortPorPrecioDescendente(List<Pan> listaPanes, int bajo, int alto) {
    if (bajo < alto) {
        int medio = (bajo + alto) / 2;
        mergeSortPorPrecioDescendente(listaPanes, bajo, medio);
        mergeSortPorPrecioDescendente(listaPanes, medio + 1, alto);
        mergePorPrecioDescendente(listaPanes, bajo, medio, alto);
    }
}

    private void mergePorPrecioAscendente(List<Pan> listaPanes, int bajo, int medio, int alto) {
    List<Pan> temp = new ArrayList<>();
    int i = bajo, j = medio + 1;
    while (i <= medio && j <= alto) {
        if (listaPanes.get(i).getPrecio() <= listaPanes.get(j).getPrecio()) {
            temp.add(listaPanes.get(i++));
        } else {
            temp.add(listaPanes.get(j++));
        }
    }
    while (i <= medio) {
        temp.add(listaPanes.get(i++));
    }
    while (j <= alto) {
        temp.add(listaPanes.get(j++));
    }
    for (int k = bajo; k <= alto; k++) {
        listaPanes.set(k, temp.get(k - bajo));
    }
}

    private void mergePorPrecioDescendente(List<Pan> listaPanes, int bajo, int medio, int alto) {
    List<Pan> temp = new ArrayList<>();
    int i = bajo, j = medio + 1;
    while (i <= medio && j <= alto) {
        if (listaPanes.get(i).getPrecio() >= listaPanes.get(j).getPrecio()) {
            temp.add(listaPanes.get(i++));
        } else {
            temp.add(listaPanes.get(j++));
        }
    }
    while (i <= medio) {
        temp.add(listaPanes.get(i++));
    }
    while (j <= alto) {
        temp.add(listaPanes.get(j++));
    }
    for (int k = bajo; k <= alto; k++) {
        listaPanes.set(k, temp.get(k - bajo));
    }
}

    @Override
    public Pan buscarPorNombre(List<Pan> listaPanes, String nombre) {
    // Implementar búsqueda binaria por nombre
    int bajo = 0;
    int alto = listaPanes.size() - 1;
    while (bajo <= alto) {
        int medio = (bajo + alto) / 2;
        int cmp = listaPanes.get(medio).getNombre().compareToIgnoreCase(nombre);
        if (cmp == 0) {
            return listaPanes.get(medio);
        } else if (cmp < 0) {
            bajo = medio + 1;
        } else {
            alto = medio - 1;
        }
    }
    return null;
}

    @Override
    public Pan buscarPorPrecio(List<Pan> listaPanes, double precio) {
    // Implementar búsqueda binaria por precio
    int bajo = 0;
    int alto = listaPanes.size() - 1;
    while (bajo <= alto) {
        int medio = (bajo + alto) / 2;
        int cmp = Double.compare(listaPanes.get(medio).getPrecio(), precio);
        if (cmp == 0) {
            return listaPanes.get(medio);
        } else if (cmp < 0) {
            bajo = medio + 1;
        } else {
            alto = medio - 1;
        }
    }
    return null;
}
}
