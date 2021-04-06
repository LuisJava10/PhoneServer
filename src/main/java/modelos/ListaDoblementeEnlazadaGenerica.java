package modelos;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author jorge
 */
public class ListaDoblementeEnlazadaGenerica implements Serializable {

    private ObjetoGenerico cabecera;

    public ListaDoblementeEnlazadaGenerica() {

        cabecera = null;
    }

    public ObjetoGenerico getCabecera() {

        return cabecera;
    }

    public void setCabecera(ObjetoGenerico pCabecera) {

        cabecera = pCabecera;
    }

    public void adicionarInicioLista(ObjetoGenerico pObjeto) {

        if (cabecera != null) {

            cabecera.setAnterior(pObjeto);
            pObjeto.setSiguiente(cabecera);
        }

        cabecera = pObjeto;
    }

    public void adicionarFinalLista(ObjetoGenerico pObjeto) {

        ObjetoGenerico temp = cabecera;

        if (cabecera == null) {
            cabecera = pObjeto;
        } else {

            while (temp.getSiguiente() != null) {

                temp = temp.getSiguiente();
            }

            temp.setSiguiente(pObjeto);
            pObjeto.setAnterior(temp);

        }

    }

    public void eliminarNodoPosicionLista(int pos) {

        ObjetoGenerico temp = cabecera;
        int cont = 1;

        if (pos == 1 && cabecera == null) {

            JOptionPane.showMessageDialog(null, "Lista vacia, no es posible eliminar");
        } else {

            if (pos == 1) {
                if (cabecera.getSiguiente() == null) {
                    cabecera = null;
                } else {
                    cabecera = cabecera.getSiguiente();
                    cabecera.setAnterior(null);
                }
            } else {

                if (cabecera == null) {
                    JOptionPane.showMessageDialog(null, "Lista vacia no es posible eliminar");
                }

                while (temp != null) {
                    temp = temp.getSiguiente();
                    cont++;

                    if (cont == pos) {
                        break;
                    }
                }

                if (temp == null) {
                    JOptionPane.showMessageDialog(null, "La posicion es mayor a la del tamaño de la lista");
                }

                if (temp.getSiguiente() == null) //Me quiere borrar el ultimo.
                {
                    temp.getAnterior().setSiguiente(null);
                    temp.setAnterior(null);
                } else {
                    temp.getSiguiente().setAnterior(temp.getAnterior());
                    temp.getAnterior().setSiguiente(temp.getSiguiente());
                }
            }
        }

    }
    /*
    public String buscarPorNombre(String pNombre)
        {
            
            ObjetoGenerico temp = cabecera;
            String uno = "";
            
            if (temp.dameNombre().ToUpper().Equals(pNombre.ToUpper()))
            {
                uno = "El nombre: " + temp.getnombre() + Environment.NewLine
                    + "La marca es: " + temp.getMarca() + Environment.NewLine
                    + "El color es: " + temp.getColor() + Environment.NewLine
                    + "El modelo es:" + temp.getModelo() + Environment.NewLine
                    + "4x4: " + temp.getTraccion4x4();

                //return uno;
            }
            else
            {
                while (temp != null)
                {
                    temp = temp.getSiguiente();
                    //contador++;

                    if (temp.getnombre().ToUpper().Equals(pNombre.ToUpper()))
                    {

                        //encontrado = true;

                        uno = "El nombre: " + temp.getnombre() + Environment.NewLine
                    + "La marca es: " + temp.getMarca() + Environment.NewLine
                    + "El color es: " + temp.getColor() + Environment.NewLine
                    + "El modelo es:" + temp.getModelo() + Environment.NewLine
                    + "4x4: " + temp.getTraccion4x4();

                        break;

                    }
                }
                //Terminando modificacion

            }

            return uno;
           }
     */
    //-----------------------------------------

}
