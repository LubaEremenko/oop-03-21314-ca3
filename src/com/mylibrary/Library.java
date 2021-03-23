package com.mylibrary;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Library extends JFrame {//add GUI components to the extended class
    private JPanel panelMain;
    private JPanel panelTop;
    private JPanel panelLeft;
    private JPanel panelRigth;
    private JList listBooks;

    private JTextField textTitle;
    private JTextField textAuthor;
    private JTextField textSummary;
    private JTextField textStatus;

    private JButton btnAdd;
    private JButton btnSave;
    private JButton btnEdit;
    private JButton btnDelet;

    private ArrayList<Books> books;
    private DefaultListModel listBooksModel;






    Library() {
        super("My ca-3 project Library"); //run the constructor
        this.setContentPane(this.panelMain); //sets the contentPane property
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //specify one of several options for the close button - Exit the application
        this.pack(); // is defined in Window class in Java and it sizes the frame so that all its contents are at or above their preferred sizes
        listBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        books = new ArrayList<Books>(); //initialise array list in constructor
        listBooksModel = new DefaultListModel();
        listBooks.setModel(listBooksModel); // add list that can handle the data about books

        btnAdd.setEnabled(true);
        btnDelet.setEnabled(true);
        btnEdit.setEnabled(true);
        btnSave.setEnabled(true);



        btnAdd.addActionListener(new ActionListener() { //is responsible for handling all action events such as when the user clicks on a component
            @Override
            public void actionPerformed(ActionEvent e) {
                btnAddClick(e); //reference on function below
            }

        });

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              btnSaveClick(e);
            }
        });


        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnEditClick(e);
            }
        });


        btnDelet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnDeletClick(e);
            }
        });

        listBooks.addListSelectionListener(new ListSelectionListener() { // function will run anytime i click on an item inside my list
            @Override
            public void valueChanged(ListSelectionEvent e) {
                listBooksSelection(e);
            }
        });


    }

    public void btnAddClick(ActionEvent e) {
        Books b = new Books(
                textTitle.getText(),
                textAuthor.getText(),
                textSummary.getText(),
                textStatus.getText()
        );
        books.add(b);
        refreshBooksList();

    }
    public void btnEditClick(ActionEvent e) {


    }

    public void btnSaveClick(ActionEvent e) {
        int bookNumber = listBooks.getSelectedIndex();
        if (bookNumber >=0) {
            Books b = books.get(bookNumber);
            b.setTitle(textTitle.getText());
            b.setAuthor(textAuthor.getText());
            b.setSummary(textSummary.getText());
            b.setStatus(textStatus.getText());
            refreshBooksList(); // update inside the books list
        }

    }

    public void btnDeletClick(ActionEvent e) {
        int a = JOptionPane.showConfirmDialog((Component)null, "Do you want to delet this book?" , "Delete", JOptionPane.YES_NO_OPTION );
        System.out.println(a);
        if (a == 0) {
            int bookNumber = listBooks.getSelectedIndex();
            if (bookNumber >= 0) {
                Books b = books.get(bookNumber);
                books.remove(bookNumber);
                refreshBooksList(); // update inside the books list
            }
        }




    }

    public void listBooksSelection(ListSelectionEvent e) {
        int bookNumber =  listBooks.getSelectedIndex();      //i can find out which item i have clicked on from my list of books. It give me the number of item that i clicked on
        if (bookNumber >= 0) {
            Books b = books.get(bookNumber); //get books from books list, it will get that book number
            textTitle.setText(b.getTitle()); //put all fields from library into the text boxes
            textAuthor.setText(b.getAuthor());
            textSummary.setText(b.getSummary());
            textStatus.setText(b.getStatus());

            btnSave.setEnabled(true);




        } else  {
            btnSave.setEnabled(false);
        }
    }


    public void refreshBooksList () { // iterate through array list and add all books in visual
        listBooksModel.removeAllElements();
        System.out.println("Removing all books from list");

        for (Books b : books) {
            System.out.println("Adding books to list: " + b.getTitle());
            listBooksModel.addElement(b.getTitle());

        }

    }


    public void addBooks(Books b) {  //make function for add books
        books.add(b);
        refreshBooksList();
    }

    public static void main(String[] args) {

        Library library = new Library();
        library.setVisible(true); //method makes the frame appear on the screen.

        Books b1 = new Books("To Kill a Mockingbird", "Harper Lee", "wonderful", "Finished" );
        Books b2 = new Books("1984", "George Orwell", "welcome to Russia", "Finished");
        Books b3 = new Books("Harry Potter and the Philosopher's Stone", "J.K. Rowling", "the best from my childhood","Finished" );
        Books b4 = new Books( "The Lord of the Rings"," by J.R.R", "boring", "Finished" );
        Books b5 = new Books("The Great Gatsby", "F. Scott Fitzgerald", "interesting", "Finished" );
        Books b6 = new Books( "Pride and Prejudice", "Jane Austen", "good old english","Finished" );
        Books b7 = new Books( "The Book Thief", "Markus Zusak", "don't like", "Finished" );

        library.addBooks(b1); //function to add books into project
        library.addBooks(b2);
        library.addBooks(b3);
        library.addBooks(b4);
        library.addBooks(b5);
        library.addBooks(b6);
        library.addBooks(b7);

        try {
            File libFile = new File("myLibrary.txt"); //create a file
            PrintStream writer = new PrintStream(libFile); // create a writer
            //loop through array and save elements
            int index = 0; // start at index 0
            while (index < books.length) {
                if (books[index] != null) {
                    writer.println(books[index]);

                }
                index = index + 1;

            }
            writer.close(); //close file connection
        }
        catch (FileNotFoundException fnf) {
            System.err.println("The file was not found");
        }
    }

}
