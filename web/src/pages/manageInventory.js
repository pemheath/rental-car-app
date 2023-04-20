import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the manageInventory page of the website.
 */

class ManageInventory extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit','redirectToViewCar', this]);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewCar);
        this.header = new Header(this.dataStore);
        }

        /**
             * Add the header to the page and load the MusicPlaylistClient.
             */

           mount() {
               document.getElementById('create-car').addEventListener('click', this.submit);

               this.header.addHeaderToPage();

               this.client = new MusicPlaylistClient();
           }

           /**
            * Method to run when the add car submit button is pressed. Call the MusicPlaylistService to create the
            * new car.
            */
             async submit(evt) {
                    evt.preventDefault();

                    const errorMessageDisplay = document.getElementById('error-message');
                    errorMessageDisplay.innerText = ``;
                    errorMessageDisplay.classList.add('hidden');

                    const createButton = document.getElementById('create-car');
                    const origButtonText = createButton.innerText;
                    createButton.innerText = 'Loading...';

                    const vin = document.getElementById('VIN').value;
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
                    this.dataStore.set('car', car);
                }


                /**
                 * When the playlist is updated in the datastore, redirect to the view playlist page.
                 */
                redirectToViewPlaylist() {
                    const playlist = this.dataStore.get('inventory');
                    if (inventory != null) {
                        window.location.href = `/inventory.html?id=${car.VIN}`;
                    }
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



