import RentalCarServiceClient from '../api/rentalCarServiceClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the manageInventory page of the website.
 */

class ManageInventory extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        }

        /**
             * Add the header to the page and load the RentalCarServiceClient.
             */

           mount() {
               document.getElementById('create-car').addEventListener('click', this.submit);

               this.header.addHeaderToPage();

               this.client = new RentalCarServiceClient();
           }

           /**
            * Method to run when the add car submit button is pressed. Call the RentalCarSerivceClient to create the
            * new car.
            */
             async submit(evt) {
                    evt.preventDefault();

                    const errorMessageDisplay = document.getElementById('error-message1');
                    errorMessageDisplay.innerText = ``;
                    errorMessageDisplay.classList.add('hidden');

                    const createButton = document.getElementById('create-car');
                    const origButtonText = createButton.innerText;
                    createButton.innerText = 'Loading...';

                    const vin = document.getElementById('VIN1').value;
                    const classOfVehicle = document.getElementById('search-criteria-extra').value;
                    const make = document.getElementById('make').value;
                    const model = document.getElementById('model').value;
                    const capacity = document.getElementById('capacity').value;
                    const year = document.getElementById('year').value;
                    const costPerDay = document.getElementById('cost-per-day').value;

                    const car = await this.client.addCar(vin, classOfVehicle, make, model, capacity, year, costPerDay, (error) => {
                        createButton.innerText = "create car";
                        errorMessageDisplay.innerText = `Error: ${error.message}`;
                        errorMessageDisplay.classList.remove('hidden');
                    });
                    console.log("the car we created is");
                    console.log(car);
                      window.location.href = `/car.html?vin=${car.vin}`;

                }

            }

            /**
             * Main method to run when the page contents have loaded.
             */
            const main = async () => {
                const manageInventory = new ManageInventory();
                manageInventory.mount();
            };

            window.addEventListener('DOMContentLoaded', main);



