import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/*
The code below this comment is equivalent to...
const EMPTY_DATASTORE_STATE = {
    'search-criteria': '',
    'search-results': [],
};

...but uses the "KEY" constants instead of "magic strings".
The "KEY" constants will be reused a few times below.
*/

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};


/**
 * Logic needed for the view playlist page of the website.
 */
class Home extends BindingClass {
    constructor() {
        super();

        // all of the functions for this class will be in this array
        this.bindClassMethods(['clientLoaded', 'mount', 'displaySearchResults', 'addPlaylistToPage', 'search', 'getHTMLForSearchResults'], this);

        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.addPlaylistToPage);
        this.dataStore.addChangeListener(this.displaySearchResults);
        console.log("home constructor"); //system.out.println in javascript
    }



    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const playlistId = document.getElementById('search-criteria').value;
        const playlist = await this.client.getPlaylist(playlistId);
        this.dataStore.set('playlist', playlist);
        document.getElementById('songs').innerText = "(loading songs...)";
        const songs = await this.client.getPlaylistSongs(playlistId);
        this.dataStore.set('songs', songs);
    }

        /**
         * When the playlist is updated in the datastore, update the playlist metadata on the page.
         */
        addPlaylistToPage() {
            const playlist = this.dataStore.get('playlist');
            if (playlist == null) {
                return;
            }

            document.getElementById('playlist-name').innerText = playlist.name;
            document.getElementById('playlist-owner').innerText = playlist.customerName;

            let tagHtml = '';
            let tag;
            for (tag of playlist.tags) {
                tagHtml += '<div class="tag">' + tag + '</div>';
            }
            document.getElementById('tags').innerHTML = tagHtml;
        }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        // Wire up the form's 'submit' event and the button's 'click' event to the search method.
        document.getElementById('search-playlists-form').addEventListener('submit', this.search);
        document.getElementById('search-btn').addEventListener('click', this.search);

        this.header.addHeaderToPage();
        console.log("added header");

        this.client = new MusicPlaylistClient();
        console.log("loaded client");
    }

        /**
         * Pulls search results from the datastore and displays them on the html page.
         */
        displaySearchResults() {
            const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
            const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);

            const searchResultsContainer = document.getElementById('search-results-container');
            const searchCriteriaDisplay = document.getElementById('search-criteria-display');
            const searchResultsDisplay = document.getElementById('search-results-display');

            if (searchCriteria === '') {
                searchResultsContainer.classList.add('hidden');
                searchCriteriaDisplay.innerHTML = '';
                searchResultsDisplay.innerHTML = '';
            } else {
                searchResultsContainer.classList.remove('hidden');
                searchCriteriaDisplay.innerHTML = `"${searchCriteria}"`;
                searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
            }
        }

    /**
     * Uses the client to perform the search,
     * then updates the datastore with the criteria and results.
     * @param evt The "event" object representing the user-initiated event that triggered this method.
     */
    async search(evt) {
        // Prevent submitting the from from reloading the page.
        evt.preventDefault();

        const searchCriteria = document.getElementById('search-criteria').value + " " + document.getElementById('search-criteria-extra').value;
        const previousSearchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        console.log(searchCriteria);
//         If the user didn't change the search criteria, do nothing
        if (previousSearchCriteria === searchCriteria) {
            return;
        }

        if (searchCriteria) {
            const results = await this.client.search(searchCriteria);
            console.log(results);
            console.log("back in home.js");


            this.dataStore.setState({
                [SEARCH_CRITERIA_KEY]: searchCriteria,
                [SEARCH_RESULTS_KEY]: results,
            });
        } else {
            this.dataStore.setState(EMPTY_DATASTORE_STATE);
        }
    }

    /**
     * Pulls search results from the datastore and displays them on the html page.
     */
    displaySearchResults() {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);
        console.log(searchResults);

        const searchResultsContainer = document.getElementById('search-results-container');
        const searchCriteriaDisplay = document.getElementById('search-criteria-display');
        const searchResultsDisplay = document.getElementById('search-results-display');

        if (searchCriteria === '') {
            searchResultsContainer.classList.add('hidden');
            searchCriteriaDisplay.innerHTML = '';
            searchResultsDisplay.innerHTML = '';
        }

        else if (searchCriteria.includes('none')) {
            searchResultsContainer.classList.remove('hidden');
            searchCriteriaDisplay.innerHTML = 'All Available Inventory';
            searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
        }

        else {
            searchResultsContainer.classList.remove('hidden');
            searchCriteriaDisplay.innerHTML = `"${searchCriteria}"`;
            searchResultsDisplay.innerHTML = this.getHTMLForSearchResults(searchResults);
        }
    }

    /**
     * Create appropriate HTML for displaying searchResults on the page.
     * @param searchResults An array of playlists objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
    getHTMLForSearchResults(searchResults) {
    console.log(searchResults);
        if (searchResults.length === 0) {
            return '<h4>No results found</h4>';
        }

        let html = '<table><tr><th>VIN</th><th>Availability</th><th>Class</th></tr>';
        for (const res of searchResults) {
            html += `
            <tr>
                <td>
                    <a href="car.html?vin=${res.vin}">${res.vin}</a>
                </td>
                <td>${res.availability}</td>
                <td>${res.classOfVehicle}</td>
            </tr>`;
        }
        html += '</table>';

        return html;
    }

}

/**
 * Main method to run when the page contents have loaded.
 //instantiate our class, call the mount function on our playlist.
 */
const main = async () => {
    const home = new Home();
    home.mount();
};

window.addEventListener('DOMContentLoaded', main); //this is what kicks off the whole thing
