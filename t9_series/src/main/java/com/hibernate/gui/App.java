package com.hibernate.gui;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.hibernate.dao.SerieDAO;
import com.hibernate.model.Serie;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class App {

	private JFrame frmCrud;
	private JTextField txtID;
	private JTextField txtNom;
	private JTextField txtTemp;
	private JTextField txtCap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmCrud.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCrud = new JFrame();
		frmCrud.getContentPane().setBackground(new Color(240, 188, 215));
		frmCrud.setTitle("CRUD");
		frmCrud.setBackground(new Color(240, 188, 215));
		frmCrud.setBounds(100, 100, 450, 390);
		frmCrud.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCrud.getContentPane().setLayout(null);
		SerieDAO serieDAO=new SerieDAO();
		Serie serie=new Serie();
		
		
		//TABLA
			//modelo
		DefaultTableModel modelo = new DefaultTableModel();
		modelo.addColumn("id");
		modelo.addColumn("nombre");
		modelo.addColumn("temporadas");
		modelo.addColumn("capitulos");
		
		//este apartado sustituye todo el try/catch de jdbc
		List<Serie> series=serieDAO.selectAllSerie();

		for(Serie se:series) {
			Object[]fila= {se.getIdserie(), se.getNombre(), se.getTemporadas(), se.getCapitulos()};
			modelo.addRow(fila);
		}
		
		JTable tabla =new JTable(modelo);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index=tabla.getSelectedRow();
				TableModel model=tabla.getModel();
				
				txtID.setText(modelo.getValueAt(index,0).toString());
				txtNom.setText(modelo.getValueAt(index, 1).toString());
				txtTemp.setText(modelo.getValueAt(index, 2).toString());
				txtCap.setText(modelo.getValueAt(index, 3).toString());
			}
		});
		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setBounds(12, 12, 426, 90);
		frmCrud.getContentPane().add(scrollPane);
		
		
		
		
		//LABEL
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(22, 114, 27, 15);
		frmCrud.getContentPane().add(lblId);
		
		JLabel lblCapitulos = new JLabel("Capitulos");
		lblCapitulos.setBounds(22, 242, 90, 15);
		frmCrud.getContentPane().add(lblCapitulos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 157, 70, 15);
		frmCrud.getContentPane().add(lblNombre);

		JLabel lblTemporadas = new JLabel("Temporadas");
		lblTemporadas.setBounds(22, 198, 103, 15);
		frmCrud.getContentPane().add(lblTemporadas);
		
		
		//TXT
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(139, 114, 114, 19);
		frmCrud.getContentPane().add(txtID);
		txtID.setColumns(10);
		
		
		txtNom = new JTextField();
		txtNom.setBounds(139, 155, 114, 19);
		frmCrud.getContentPane().add(txtNom);
		txtNom.setColumns(10);
		
		
		txtTemp = new JTextField();
		txtTemp.setBounds(139, 196, 114, 19);
		frmCrud.getContentPane().add(txtTemp);
		txtTemp.setColumns(10);
		
		
		txtCap = new JTextField();
		txtCap.setBounds(139, 240, 114, 19);
		frmCrud.getContentPane().add(txtCap);
		txtCap.setColumns(10);
		
		
		//BOTONES
			//insertar (hecho)
		JButton btnInsert = new JButton("Añadir");
		btnInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Serie serieIns=new Serie();
				String nombre=txtNom.getText();
				int temp=Integer.parseInt(txtTemp.getText());
				int cap=Integer.parseInt(txtCap.getText());
				
				//actualizar la clase
				serieIns=new Serie(nombre, temp, cap);
				
				//actualizar base de datos
				serieDAO.insertSerie(serieIns);
				
				//actualizar la tabla instantaneamente
				modelo.setRowCount(0);
				List<Serie> series=serieDAO.selectAllSerie();
				for(Serie se:series) {
					Object[]fila= {se.getIdserie(), se.getNombre(), se.getTemporadas(), se.getCapitulos()};
					modelo.addRow(fila);
				}
				txtNom.setText("");
				txtTemp.setText("");
				txtCap.setText("");
			}
		});
		btnInsert.setBounds(22, 316, 90, 25);
		frmCrud.getContentPane().add(btnInsert);
		
		
			//actualizar
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Serie serieAct=new Serie();
				
				int id=Integer.parseInt(txtID.getText());
				String nombre=txtNom.getText();
				int temp=Integer.parseInt(txtTemp.getText());
				int cap=Integer.parseInt(txtCap.getText());
				
				//encontrar la clase especifica 
				serieAct=serieDAO.selectSerieById(id);
				
				//actualizar la clase
				serieAct.setNombre(nombre);
				serieAct.setTemporadas(temp);
				serieAct.setCapitulos(cap);
				
				//actualizar base de datos
				serieDAO.updateSerie(serieAct);
				
				//actualizar la tabla instantaneamente
				modelo.setRowCount(0);
				List<Serie> series=serieDAO.selectAllSerie();
				for(Serie se:series) {
					Object[]fila= {se.getIdserie(), se.getNombre(), se.getTemporadas(), se.getCapitulos()};
					modelo.addRow(fila);
				}
				txtID.setText("");
				txtNom.setText("");
				txtTemp.setText("");
				txtCap.setText("");
			}
		});
		btnActualizar.setBounds(162, 316, 117, 25);
		frmCrud.getContentPane().add(btnActualizar);
		
		
			//borrar
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		btnBorrar.setBounds(331, 316, 90, 25);
		frmCrud.getContentPane().add(btnBorrar);
		
		
	}
}
