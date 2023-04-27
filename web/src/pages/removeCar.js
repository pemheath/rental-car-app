import RentalCarServiceClient from '../api/rentalCarServiceClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';



const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};


class RemoveCar extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'removeCar'], this);

        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        console.log("RemoveCar constructor");
    }


    /**
     * Add the header to the page and load the RentalCarServiceClient
     */
    mount() {
        document.getElementById("remove").addEventListener('click',this.removeCar);
       // document.getElementById('view-car').addEventListener('click', this.getCar);

        this.client = new RentalCarServiceClient();

    }

    async removeCar (){

        const errorMessageDisplay = document.getElementById('error-message-remove');
        errorMessageDisplay.innerText = '';
        errorMessageDisplay.classList.add('hidden');

        const deleteResultsContainer = document.getElementById('remove-car-container');
        const deleteCriteriaDisplay = document.getElementById('delete-criteria');
        const deleteResultsDisplay = document.getElementById('remove-car-display');

        const vinDelete = document.getElementById('vin-for-delete').value;

        const carResult = await this.client.deleteCar(vinDelete, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');

            deleteResultsContainer.classList.add('hidden');
            deleteCriteriaDisplay.innerHTML = '';
            deleteResultsDisplay.innerHTML = '';
            });

        const html = '<table><tr><th>Make</th><th>Model</th><th>Year</th><th>Class</th><th>Capacity</th><th>Availability</th><th>CostPerDay</th></tr>' +
                    '<tr><td>'+ carResult.make + '</td>' +
                     '<td>'+ carResult.model + '</td>'  +
                     '<td>'+ carResult.year + '</td>'  +
                     '<td>'+ carResult.classOfVehicle + '</td>'  +
                     '<td>'+ carResult.capacity + '</td>'  +
                     '<td>'+ carResult.availability + '</td>' +
                     '<td>'+ carResult.costPerDay + '</td></tr>'
                      '</table>';

        deleteResultsContainer.classList.remove('hidden');
        deleteCriteriaDisplay.innerHTML = '<p> The below car has now been deleted and no longer exists in inventory</p>';
        deleteResultsDisplay.innerHTML = html;
      }

    }


/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const removeCar = new RemoveCar();
    removeCar.mount();
};

window.addEventListener('DOMContentLoaded', main);