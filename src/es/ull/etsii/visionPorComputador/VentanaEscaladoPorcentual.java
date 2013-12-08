package es.ull.etsii.visionPorComputador;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VentanaEscaladoPorcentual extends JFrame {

  private static final long serialVersionUID = 7965342468951370508L;

  public static final int ANCHO = 245;
  public static final int ALTO = 120;
  public static final int NUM_COLS_FIELD = 5;
  public static final boolean RESIZABLE = false;

  private static final int VECINO = 0;
  private static final int BILINEAL = 1;

  JTextField textFieldAncho;
  JTextField textFieldAlto;
  JButton botonOk;
  Interfaz interfazRef;
  Imagen imagen;
  int tipoInterpolacion;

  public VentanaEscaladoPorcentual(Interfaz interfazRef, Imagen imagen) {
    setLayout(new FlowLayout());
    setInterfazRef(interfazRef);
    setImagen(imagen);
    setBotonOk(new JButton("Aceptar"));
    JLabel anchoOriginal = new JLabel("          Ancho = " + imagen.getAncho());
    JLabel altoOriginal = new JLabel("Alto = " + imagen.getAlto()
        + "            ");
    add(anchoOriginal);
    add(altoOriginal);
    add(new JLabel("Ancho: "));
    setTextFieldAncho(new JTextField(NUM_COLS_FIELD));
    add(getTextFieldAncho());
    add(new JLabel("%"));
    add(new JLabel("Alto: "));
    setTextFieldAlto(new JTextField(NUM_COLS_FIELD));
    add(getTextFieldAlto());
    add(new JLabel("%"));
    getBotonOk().addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        String textoAncho = getTextFieldAncho().getText();
        String textoAlto = getTextFieldAlto().getText();
        try {
          int porcentajeAncho = Integer.parseInt(textoAncho);
          int porcentajeAlto = Integer.parseInt(textoAlto);
          // TODO crear nueva imagen escalada (porcentaje negativo es reducir,
          // porcentaje positivo es aumentar)
          
          
          dispose();
        } catch (NumberFormatException noNumero) {
          System.err
              .println(getTextFieldAncho().getText() + " no es un número");
          System.err.println(getTextFieldAlto().getText() + " no es un número");
          getTextFieldAncho().setText("");
          getTextFieldAlto().setText("");
        } catch (NullPointerException noHayImagen) {
          System.err.println("No hay imágenes abiertas");
          dispose();
        }
      }
    });

    setTipoInterpolacion(VECINO);
    ButtonGroup agrupBoton = new ButtonGroup();
    JRadioButton radioVecino = new JRadioButton("Vecino");
    radioVecino.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        setTipoInterpolacion(VECINO);
      }
    });
    agrupBoton.add(radioVecino);
    JRadioButton radioBilineal = new JRadioButton("Bilineal");
    radioBilineal.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        setTipoInterpolacion(BILINEAL);
      }
    });
    agrupBoton.add(radioBilineal);
    add(radioVecino);
    add(radioBilineal);

    add(getBotonOk());
    setResizable(RESIZABLE);
    setSize(ANCHO, ALTO);
    setVisible(true);
  }

  private JTextField getTextFieldAlto() {
    return textFieldAlto;
  }

  private void setTextFieldAlto(JTextField textFieldAlto) {
    this.textFieldAlto = textFieldAlto;
  }

  private JTextField getTextFieldAncho() {
    return textFieldAncho;
  }

  private void setTextFieldAncho(JTextField textFieldAncho) {
    this.textFieldAncho = textFieldAncho;
  }

  private JButton getBotonOk() {
    return botonOk;
  }

  private void setBotonOk(JButton botonOk) {
    this.botonOk = botonOk;
  }

  private Interfaz getInterfazRef() {
    return interfazRef;
  }

  private void setInterfazRef(Interfaz interfazRef) {
    this.interfazRef = interfazRef;
  }

  private Imagen getImagen() {
    return imagen;
  }

  private void setImagen(Imagen imagen) {
    this.imagen = imagen;
  }

  private int getTipoInterpolacion() {
    return tipoInterpolacion;
  }

  private void setTipoInterpolacion(int tipoInterpolacion) {
    this.tipoInterpolacion = tipoInterpolacion;
  }
}
