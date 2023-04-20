import MusicPlaylistClient from '../api/musicPlaylistClient';
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
        this.bindClassMethods(['clientLoaded', 'mount', 'removeCar'], this);

        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        console.log("RemoveCar constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
     console.log("calling client loaded");

     const urlParams = new URLSearchParams(window.location.search);
     const vin = urlParams.get('vin');

     if(vin){
         document.getElementById('car-name').innerText = "Loading Car ...";
         const car = await this.client.getCar(vin);
         console.log(car);
         this.dataStore.setState({
             [SEARCH_CRITERIA_KEY]: vin,
             [SEARCH_RESULTS_KEY]: car,
         });
         document.getElementById('car-name').innerText = "CAR FOUND!";
         console.log(dataStore.get(SEARCH_RESULTS_KEY));
     }

    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById("remove").addEventListener('click',this.removeCar);
       // document.getElementById('view-car').addEventListener('click', this.getCar);
        this.header.addHeaderToPage();
        this.client = new MusicPlaylistClient();
        this.clientLoaded();
    }





    async removeCar (){

        const errorMessageDisplay = document.getElementById('error-message-remove');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const vinDelete = document.getElementById('vin-for-delete').value;
        console.log("vin is");
        console.log(vinDelete);

        if (vinDelete) {
            const carResult = await this.client.deleteCar(vinDelete);
            if (carResult==null){
                errorMessageDisplay.innerText = `No Car Found`;
                errorMessageDisplay.classList.remove('hidden');
                document.getElementById('car-name').innerText = "No Car Found";

                createButton.innerText = 'Search New Car';

                const searchResultsContainer = document.getElementById('search-results-container');
                const searchCriteriaDisplay = document.getElementById('search-criteria-display');
                const searchResultsDisplay = document.getElementById('search-results-display');

                searchResultsContainer.classList.add('hidden');
                searchCriteriaDisplay.innerHTML = '';
                searchResultsDisplay.innerHTML = '';

            }

                let html = '<table><tr><th>Make</th><th>Model</th><th>Year</th><th>Class</th><th>Capacity</th><th>Availability</th><th>CostPerDay</th></tr>' +
                    '<tr><td>'+ carResult.make + '</td>' +
                     '<td>'+ carResult.model + '</td>'  +
                     '<td>'+ carResult.year + '</td>'  +
                     '<td>'+ carResult.classOfVehicle + '</td>'  +
                     '<td>'+ carResult.capacity + '</td>'  +
                     '<td>'+ carResult.availability + '</td>' +
                     '<td>'+ carResult.costPerDay + '</td></tr>'
                      '</table>';

            document.getElementById('remove-car-display').innerText = html;
            console.log(dataStore.get(SEARCH_RESULTS_KEY));
        }
    }

   /**
     * Create appropriate HTML for displaying searchResults on the page.
     * @param searchResults An array of playlists objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const removeCar = new RemoveCar();
    removeCar.mount();
};

window.addEventListener('DOMContentLoaded', main);