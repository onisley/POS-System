package pos.presentationLayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.*;

import pos.domainlayer.*;

public class ProcessSaleJFrame extends JFrame {
	
	private static final String TAG = "Status: ";
	private static final int MAKE_NEW_SALE = 1;
	private static final int ENTER_ITEM = 2;
	private static final int END_SALE = 3;
	private static final int CALCULATE_TAX = 4;
	private static final int APPLY_DISCOUNT = 5;
	private static final int MAKE_PAYMENT = 6;
	
	private Connection myConnection;
	private Statement myStatement;
	private ResultSet myResultSet;
	
	Register mRegister;
	Sale sale;
	
	// GUI instance
	private JButton btnNewSale;
	private JButton btnEnterItem;
	private JButton btnEndSale;
	private JButton btnCalculateTax;
	private JButton btnApplyDiscount;
	private JButton btnMakePayment;
	
	private JComboBox<String> txtItemID;
	private JTextField txtQuantity;
	private JTextField txtDescription;
	
	private JTextField txtTotal;
	private JRadioButton radioTaxMaster;
	private JRadioButton radioTaxGold;
	
	private JTextField txtTotalWithTax;
	private JRadioButton radioCustomer;
	private JRadioButton radioStore;
	
	private JTextField txtTotalAfterDiscount;
	private JTextField txtAmount;
	private JTextField txtBalance;
	
	private JTextArea txtDisplay;
	
	// Data instance
	private Set<String> strItemID;
	
	public ProcessSaleJFrame(Register register) {
		
		super("POS System (학번:20161050/이름:권소영)"); // better than setTitle()
		
		mRegister = register;
		
		buildGUI(); // Build Components
		registerEventHandler(); // add EventHandler
		
		// Add itemID to ComboBox
		strItemID = register.getCatalog();
		for (Iterator i = strItemID.iterator(); i.hasNext();) {
			txtItemID.addItem(i.next().toString());
		}
		
		this.pack(); // 화면을 구성할 수 있는 적당한 크기로 packing
		this.setVisible(true);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	} // end ProcessSaleJFrame(Register register)
	
	private void buildGUI() {
		
		// *1. Make New Sale()
		btnNewSale = new JButton("1. makeNewSale()");
		
		// *2. Enter Item(반복)
		JPanel panItemID = new JPanel();
		JLabel lblItemID = new JLabel("Item ID:");
		txtItemID = new JComboBox<String>();
		panItemID.add(lblItemID);
		panItemID.add(txtItemID);
		
		JPanel panQuantity = new JPanel();
		JLabel lblQuantity = new JLabel("Quantity:");
		txtQuantity = new JTextField(10);
		panQuantity.add(lblQuantity);
		panQuantity.add(txtQuantity);
		
		JPanel panDescription = new JPanel();
		JLabel lblDescription = new JLabel("Description: ");
		txtDescription = new JTextField(10);
		panDescription.add(lblDescription);
		panDescription.add(txtDescription);
		
		btnEnterItem = new JButton("2. enterItem() (반복)");
		
		// *3. End Sale()
		btnEndSale = new JButton("3. endSale()");
		
		// Display Current Total
		JPanel panTotal = new JPanel();
		JLabel lblTotal = new JLabel("Current Total: ");
		txtTotal = new JTextField(10);
		panTotal.add(lblTotal);
		panTotal.add(txtTotal);
		
		// *4. Calculate Tax();
		JPanel panTax = new JPanel();
		radioTaxMaster = new JRadioButton("TaxMaster");
		radioTaxGold = new JRadioButton("GoodAsGoldTaxPro");
		panTax.add(radioTaxMaster);
		panTax.add(radioTaxGold);
		ButtonGroup groupTax = new ButtonGroup();
		groupTax.add(radioTaxMaster);
		groupTax.add(radioTaxGold);
		
		btnCalculateTax = new JButton("4. calculateTax()");
		
		// Display Total with Tax
		JPanel panTotalWithTax = new JPanel();
		JLabel lblTotalWithTax = new JLabel("Total with Tax: ");
		txtTotalWithTax = new JTextField(10);
		panTotalWithTax.add(lblTotalWithTax);
		panTotalWithTax.add(txtTotalWithTax);
		
		// *5. Apply Discount()
		JPanel panDiscount = new JPanel();
		radioCustomer = new JRadioButton("BestForCustomer");
		radioStore = new JRadioButton("BestForStore");
		panDiscount.add(radioCustomer);
		panDiscount.add(radioStore);
		ButtonGroup groupDiscount = new ButtonGroup();
		groupDiscount.add(radioCustomer);
		groupDiscount.add(radioStore);
		
		JPanel panAmount = new JPanel();
		JLabel lblAmount = new JLabel("Amount");
		txtAmount = new JTextField(10);
		panAmount.add(lblAmount);
		panAmount.add(txtAmount);
		
		btnApplyDiscount = new JButton("5. applyDiscount()");
		
		// Display Total after Discount
		JPanel panTotalAfterDiscount = new JPanel();
		JLabel lblTotalAfterDiscount = new JLabel("Total after Discount()");
		txtTotalAfterDiscount = new JTextField(10);
		panTotalAfterDiscount.add(lblTotalAfterDiscount);
		panTotalAfterDiscount.add(txtTotalAfterDiscount);
		
		// *6. Make Payment()
		btnMakePayment = new JButton("6. makePayment()");
		
		JPanel panBalance = new JPanel();
		JLabel lblBalance = new JLabel("Balance");
		txtBalance = new JTextField(10);
		panBalance.add(lblBalance);
		panBalance.add(txtBalance);
		
		// Display Area
		txtDisplay = new JTextArea();
		JScrollPane displayArea = new JScrollPane(txtDisplay);
		
		// *setEnabled = false
		txtDescription.setEditable(false);
		txtTotal.setEditable(false);
		txtTotalWithTax.setEditable(false);
		txtTotalAfterDiscount.setEditable(false);
		txtBalance.setEditable(false);
		setMenuStatus(MAKE_NEW_SALE, true);
		setMenuStatus(ENTER_ITEM, false);
		setMenuStatus(END_SALE, false);
		setMenuStatus(CALCULATE_TAX, false);
		setMenuStatus(APPLY_DISCOUNT, false);
		setMenuStatus(MAKE_PAYMENT, false);
		txtDisplay.setEditable(false);
		
		// *get ContentPane
		Container cp = this.getContentPane();
		cp.setLayout(new GridLayout(0, 2));
		
		// *add Components to ContentPane
		JPanel panBorderCenter = new JPanel();
		panBorderCenter.setLayout(new GridLayout(0, 1));
		panBorderCenter.add(btnNewSale);
		panBorderCenter.add(panItemID);
		panBorderCenter.add(panQuantity);
		panBorderCenter.add(panDescription);
		panBorderCenter.add(btnEnterItem);
		panBorderCenter.add(panTotal);
		panBorderCenter.add(btnEndSale);
		panBorderCenter.add(panTax);
		panBorderCenter.add(btnCalculateTax);
		panBorderCenter.add(panTotalWithTax);
		panBorderCenter.add(panDiscount);
		panBorderCenter.add(btnApplyDiscount);
		panBorderCenter.add(panTotalAfterDiscount);
		panBorderCenter.add(panAmount);
		panBorderCenter.add(btnMakePayment);
		panBorderCenter.add(panBalance);
		
		cp.add(panBorderCenter);
		cp.add(displayArea);
		
	} // end buildGUI()
	
	private void registerEventHandler() {
		// 1. Make New Sale()
		btnNewSale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				sale = mRegister.makeNewSale();
				
				// clear txtField
				txtTotal.setText("");
				txtAmount.setText("");
				txtBalance.setText("");
				
				setMenuStatus(MAKE_NEW_SALE, false);
				setMenuStatus(ENTER_ITEM, true);
				setMenuStatus(END_SALE, true);
				
				txtDisplay.append("새 판매가 시작되었습니다.");
			}
			
		});
		
		// 2. Enter Item(반복)
		btnEnterItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					ItemID id = new ItemID(txtItemID.getSelectedItem().toString());
					int quantity = Integer.parseInt(txtQuantity.getText());
					
					mRegister.enterItem(id, quantity);
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"수량 란에는 숫자만 입력 가능", "enterItem() 오류", JOptionPane.ERROR_MESSAGE);
				}
				
				txtItemID.setSelectedItem(0);
				txtQuantity.setText("");
				
				System.out.println(TAG + "btnEnterItem Performed");
			}
		});
		
		// 3. End Sale()
		btnEndSale.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				endSale();
				
				setMenuStatus(ENTER_ITEM, false);
				setMenuStatus(END_SALE, false);
				setMenuStatus(MAKE_PAYMENT, true);
				
				System.out.println(TAG + "btnEndSale Performed");
			}
		});
		
		// 4. Make Payment()
		btnMakePayment.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				makePayment();
				
				setMenuStatus(MAKE_PAYMENT, false);
				setMenuStatus(MAKE_NEW_SALE, true);
				
				System.out.println(TAG + "btnMakePayment Performed");
			}
		});
		
	} //end registerEventHandler();
	
	private void endSale() {
		// display total amount
		txtTotal.setText(sale.getTotal().toString());
		
		// end sale
		mRegister.endSale();
	}
	
	private void makePayment() {
		// make payment
		int amount = Integer.parseInt(txtAmount.getText());
		sale.makePayment(new Money(amount));
		
		// get balance
		txtBalance.setText(sale.getBalance().toString());
	}
	
	private void setMenuStatus(int index, boolean set) {
		
		switch (index) {
		case MAKE_NEW_SALE:
			btnNewSale.setEnabled(set);
			break;
		case ENTER_ITEM:
			txtItemID.setEnabled(set);
			txtQuantity.setEnabled(set);
			btnEnterItem.setEnabled(set);
			break;
		case END_SALE:
			btnEndSale.setEnabled(set);
			break;
		case CALCULATE_TAX:
			radioTaxMaster.setEnabled(set);
			radioTaxGold.setEnabled(set);
			btnCalculateTax.setEnabled(set);
		case APPLY_DISCOUNT:
			radioCustomer.setEnabled(set);
			radioStore.setEnabled(set);
			btnApplyDiscount.setEnabled(set);
		case MAKE_PAYMENT:
			txtAmount.setEnabled(set);
			btnMakePayment.setEnabled(set);
		}
		
	} // end setEnabledMenu()
	
}
