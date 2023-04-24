import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';



const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_RESULTS_KEY]: [],
};

/**
 * Logic needed for the create playlist page of the website.
 */
class UpdateCar extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'viewCarFirst', 'submit', 'displaySearchResults', 'getHTMLForSearchResults'], this);
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.dataStore.addChangeListener(this.displaySearchResults);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('view-button').addEventListener('click', this.viewCarFirst);
        document.getElementById('update-button').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.client = new MusicPlaylistClient();
    }

    /**
     * Display the car to update before user selects update information
     */
    async viewCarFirst() {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const vin = document.getElementById('vin').value;

        const car = await this.client.getCar(vin, (error) => {

        errorMessageDisplay.innerText = `Error: ${error.message}`;
        errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.setState({
            [SEARCH_RESULTS_KEY]: car,
        });
        document.getElementById('car-display-header').innerHTML = "Current Car";

    }

    /**
     * Method to run when the create playlist submit button is pressed. Call the MusicPlaylistService to create the
     * playlist.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const vin = document.getElementById('vin').value;
        const availability = document.getElementById('availability').value;
        const costPerDay = document.getElementById('cost-per-day').value;

        const car = await this.client.updateCar(vin, availability, costPerDay, (error) => {

        errorMessageDisplay.innerText = `Error: ${error.message}`;
        errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.setState({
            [SEARCH_RESULTS_KEY]: car,
        });
        document.getElementById('car-display-header').innerHTML = "Updated Car";

    }

    /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
    displaySearchResults() {
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);

        console.log(searchResults);

        const searchResultsContainer = document.getElementById('search-results-container');
        const searchResultsDisplay = document.getElementById('search-results-display');

        searchResultsContainer.classList.add('hidden');
        searchResultsContainer.classList.remove('hidden');
        searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
    }

    getHTMLForSearchResults(car) {
        if (car.length === 0) {
            return '<h4>No results found</h4>';
        }

        let html = '<table><tr><th>Make</th><th>Model</th><th>Year</th><th>Class</th><th>Capacity</th><th>Availability</th><th>CostPerDay</th></tr>' +
            '<tr><td>'+ car.make + '</td>' +
            '<td>'+ car.model + '</td>'  +
            '<td>'+ car.year + '</td>'  +
            '<td>'+ car.classOfVehicle + '</td>'  +
            '<td>'+ car.capacity + '</td>'  +
            '<td>'+ car.availability + '</td>' +
            '<td>'+ car.costPerDay + '</td></tr>'
            '</table>';

        return html;
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const updateCar = new UpdateCar();
    updateCar.mount();
};

window.addEventListener('DOMContentLoaded', main);
