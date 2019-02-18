package boundary;

import java.util.ArrayList;

import control.DataLogic;
import entity.BusinessCompany;
import entity.Consts;
import entity.Miner;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;


public class ProfileController {

	private String currentMinerAddress = Consts.currentMinerAddress; //the current miner that  is logged in
	private boolean isWorker = Consts.isWorker;
	
	private ArrayList<Miner> allMiners = DataLogic.getInstance().getAllMiners();
	private ArrayList<BusinessCompany> allBussinessMiners= DataLogic.getInstance().getAllBusinessCompanys();



	@FXML
	private TextField tf_address;

	@FXML
	private TextField tf_username;

	@FXML
	private TextField tf_password;

	@FXML
	private TextField tf_email;

	@FXML
	private RadioButton rb_privateMiner;

	@FXML
	private RadioButton rb_businerrMiner;

	@FXML
	private TextField tf_contactName;

	@FXML
	private TextField tf_contactPhone;

	@FXML
	private TextField tf_contactEmail;

	@FXML
	private TextField tf_digitalProfit;

	@FXML
	private Button btn_marketPredictionRep;

	@FXML
	private Button btn_profileUpdate;

	@FXML
	private Button btn_profileSave;


	@FXML
	public void initialize() {




		String minerID =  currentMinerAddress;
		Miner miner = null;
		BusinessCompany businessCompany = null;
		boolean isBussiness = false;

		// get miner details from DB
		for (Miner m : allMiners) {
			if (m.getUniqueAddress().equals(minerID)) {
				miner = m;
				break;
			}
		}

		// check if miner is business or private, if business, get business details from DB
		for (BusinessCompany b : allBussinessMiners) {
			if (miner.getUniqueAddress().equals(b.getUniqueAddress())) {
				businessCompany = b;
				isBussiness = true;
				break;
			}
		}

		tf_address.setText(miner.getUniqueAddress());
		tf_digitalProfit.setText(Double.toString(miner.getDigitalProfit()));
		tf_email.setText(miner.getEmail());
		tf_username.setText(miner.getName());
		tf_password.setText(miner.getPassword());



		if (isBussiness == true) {
			rb_businerrMiner.setSelected(true);
			rb_privateMiner.setSelected(false);

			tf_contactName.setText(businessCompany.getContactName());
			tf_contactEmail.setText(businessCompany.getContactEmail());
			tf_contactPhone.setText(businessCompany.getContactPhone());

		}
		else {
			rb_businerrMiner.setSelected(false);
			rb_privateMiner.setSelected(true);

		}



		tf_address.setEditable(false);
		tf_username.setEditable(false);
		tf_password.setEditable(false);
		tf_email.setEditable(false);
		tf_contactName.setEditable(false);
		tf_contactPhone.setEditable(false);
		tf_contactEmail.setEditable(false);
		tf_digitalProfit.setEditable(false);

		rb_businerrMiner.setDisable(true);
		rb_privateMiner.setDisable(true);

	}

	@FXML
	void runMarketPredictionRep(MouseEvent event) {

	}

	@FXML
	void saveProfile(MouseEvent event) {

		String miner = tf_address.getText();
		boolean isPrivate = rb_privateMiner.isSelected();
		boolean isBussiness = rb_businerrMiner.isSelected();

		if (!isValid())
			return;

		// update in db

		if (isPrivate) {
			if (DataLogic.getInstance().editMiner(tf_address.getText(), tf_username.getText(),
					tf_password.getText(), Double.parseDouble(tf_digitalProfit.getText()), tf_email.getText())) {

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Successfully updated!");
				alert.setContentText("Successfully updated private miner");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Failed to update!");
				alert.setContentText("Failed to update private miner");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();
			}
		}

		else if (isBussiness) {

			// check if miner was business before
			boolean wasBusiness = false;
			for (BusinessCompany b : allBussinessMiners) {
				if (miner.equals(b.getUniqueAddress())) {
					wasBusiness = true;
					break;
				}
			}

			// update miner details in miner tbl
			if (DataLogic.getInstance().editMiner(tf_address.getText(), tf_username.getText(),
					tf_password.getText(), Double.parseDouble(tf_digitalProfit.getText()), tf_email.getText())) {

				// if was business, update miner details in company tbl
				if (wasBusiness) {
					if (DataLogic.getInstance().editBusinessCompany(tf_address.getText(), tf_contactName.getText(),
							tf_contactPhone.getText(), tf_contactEmail.getText()))  {

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Successfully updated!");
						alert.setContentText("Successfully update business miner");
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.showAndWait();

					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Failed to update!");
						alert.setContentText("Failed to update business miner");
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.showAndWait();
					}
				
				// if was not business, add miner details in company tbl
				} else {

					if (DataLogic.getInstance().addBusinessCompany(tf_address.getText(), tf_contactName.getText(),
							tf_contactPhone.getText(), tf_contactEmail.getText()))  {

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Successfully updated!");
						alert.setContentText("Successfully update business miner");
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.showAndWait();

					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Failed to update!");
						alert.setContentText("Failed to update business miner");
						alert.initModality(Modality.APPLICATION_MODAL);
						alert.showAndWait();
					}
				}

			} else { // miner update failed
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Failed to update!");
				alert.setContentText("Failed to update miner");
				alert.initModality(Modality.APPLICATION_MODAL);
				alert.showAndWait();
			}
		}

		rb_businerrMiner.setDisable(true);
		rb_privateMiner.setDisable(true);

	}


	public boolean isValid () {

		boolean isVaild = true;
		boolean isPrivate = rb_privateMiner.isSelected();
		boolean isBussiness = rb_businerrMiner.isSelected();

		System.out.println("isPrivate" + isPrivate + "  isBussiness"+ isBussiness);

		// valid info, cant be empty fields
		if (tf_username.getText().equals("") ||	tf_password.getText().equals("") ||	tf_email.getText().equals("")) {
			isVaild = false;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fields are empty!");
			alert.setContentText("Please fill all fields");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}

		// valid info, can be private or business, not both
		else if ((isPrivate && isBussiness) || (!isPrivate && !isBussiness)) {
			isVaild = false;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Choose one option!");
			alert.setContentText("Please choose if you are an business or a private miner (Can't be both)");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		} 	

		else {
			// validate info if private
			if (isPrivate) {
				if (!(tf_contactName.getText().equals("") && tf_contactPhone.getText().equals("") && tf_contactEmail.getText().equals(""))) {
					isVaild = false;
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Miner is set as private!");
					alert.setContentText("Miner is set as private");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.showAndWait();
				}
			}

			// validate info if business
			else if (isBussiness) {
				if (tf_contactName.getText().equals("") && tf_contactPhone.getText().equals("") && tf_contactEmail.getText().equals("")) {
					isVaild = false;
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Feilds are empty!");
					alert.setContentText("Please fill all fields");
					alert.initModality(Modality.APPLICATION_MODAL);
					alert.showAndWait();
				}
			}
		}
		return isVaild;

	}


	@FXML
	void updateProfile(MouseEvent event) {

		tf_username.setEditable(true);
		tf_password.setEditable(true);
		tf_email.setEditable(true);
		tf_contactName.setEditable(true);
		tf_contactPhone.setEditable(true);
		tf_contactEmail.setEditable(true);

		rb_businerrMiner.setDisable(false);
		rb_privateMiner.setDisable(false);

	}

}
