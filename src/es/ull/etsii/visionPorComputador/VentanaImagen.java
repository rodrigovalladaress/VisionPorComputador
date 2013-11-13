/** @author Daniel Afonso González
 *  @author Rodrigo Valladares Santana
 *  @version 1.2, 13/11/13
 *  
 *  Proyecto de Visión Por Computador 2013/14
 *  
 *  Ventana en la que se mostrará la imagen que será posteriormente modificada
 *  en la interfaz del programa.
 *  
 *  Tiene una clase interna llamada PanelImagen que es donde se muestra la 
 *  imagen gracias a Graphics2D.
 *  
 *  Versión 1.2 La clase reconoce en qué posición esta el puntero del ratón para 
 *  devolver el valor del píxel en el que está.
 *  
 *  Versión 1.1 La ventana muestra los botones de minimizar, maximizar y 
 *  cerra. Se puede cambiar su tamaño.
 */
package es.ull.etsii.visionPorComputador;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class VentanaImagen extends JInternalFrame {

  public static final boolean RESIZABLE = true;
  public static final boolean CLOSABLE = true;
  public static final boolean MAXIMIZABLE = true;
  public static final boolean ICONIFIABLE = true;

  private static final long serialVersionUID = 8689935453546765653L;
  private Imagen imagen;
  private PanelImagen panelImagen;
  private Interfaz interfazRef;

  /**
   * Constructor que recibe la Imagen que va a mostrar y el titulo de la ventana
   * @param imagen
   * @param nombre
   */
  public  VentanaImagen(Imagen imagen, String titulo, Interfaz interfazRef) {
    // TODO cambiar la interfaz del frame interno para que esté acorde al resto
    // TODO hacer que cuando se cierre una ventana se elimine de ListaVentana
    // de Interfaz
    super(titulo, RESIZABLE, CLOSABLE, MAXIMIZABLE, ICONIFIABLE);
    setImagen(imagen);
    setPanelImagen(new PanelImagen());
    setSize(getImagen().getAncho(), getImagen().getAlto());
    add(getPanelImagen());
    setVisible(true);
    setInterfazRef(interfazRef);
  }

  public class PanelImagen extends JPanel {
    private static final long serialVersionUID = 8039435365744075304L;

    public PanelImagen() {
      addMouseMotionListener(new OyenteRaton());
    }
    
    // TODO hacer scroll de la imagen
    
    /**
     * Muestra la imagen en el panel
     */
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(getImagen().getImagen(), 0, 0, this);
    }
    
    private class OyenteRaton implements MouseMotionListener {
      
      public OyenteRaton() {}
      
      private int posXRaton;
      private int posYRaton;
      
      @Override
      public void mouseDragged(MouseEvent arg0) {
        // TODO Hacer selección de la imagen
      }

      @Override
      public void mouseMoved(MouseEvent mouse) {
        int x = mouse.getX();
        int y = mouse.getY();
        if((x < getImagen().getAncho() && (y < getImagen().getAlto()))) {
          setPosXRaton(x);
          setPosYRaton(y);
          getInterfazRef().actualizarDatosPixelActivo(x, y, getPixelActivo());
        }
      }

      private int getPixelActivo() {
        return getImagen().getPixel(getPosXRaton(), getPosYRaton());
      }

      private int getPosXRaton() {
        return posXRaton;
      }

      private void setPosXRaton(int posXRaton) {
        this.posXRaton = posXRaton;
      }

      private int getPosYRaton() {
        return posYRaton;
      }

      private void setPosYRaton(int posYRaton) {
        this.posYRaton = posYRaton;
      }
      
    }

  }

  /**
   * Devuelve la Imagen
   * @return
   */
  public Imagen getImagen() {
    return imagen;
  }

  /**
   * 
   * @param imagen
   */
  private void setImagen(Imagen imagen) {
    this.imagen = imagen;
  }

  /**
   * Wrap de getTitle()
   * @return
   */
  public String getTitulo() {
    return getTitle();
  }

  /**
   * Devuelve el JPanel donde se muestra la imagen
   * @return
   */
  private PanelImagen getPanelImagen() {
    return panelImagen;
  }

  /**
   * 
   * @param panelImagen
   */
  private void setPanelImagen(PanelImagen panelImagen) {
    this.panelImagen = panelImagen;
  }
  
  private Interfaz getInterfazRef() {
    return interfazRef;
  }

  private void setInterfazRef(Interfaz interfazRef) {
    this.interfazRef = interfazRef;
  }

}