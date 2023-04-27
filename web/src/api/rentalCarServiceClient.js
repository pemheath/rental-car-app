import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the RentalCarService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class RentalCarServiceClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'search', 'getCar','deleteCar', 'addCar'];

        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();

    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined; //declared but not yet assigned a value
            }

            return await this.authenticator.getCurrentUserInfo(); //if the use is logged in, return their info
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

        /**
         * Get the identity of the current user
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The user information for the current user.
         */

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }


    /**
     * Search for a list of cars based on available criteria.
     * @param criteria A string containing search criteria to pass to the API.
     * @returns The cars that match the search criteria.
     */
    async search(criteria, errorCallback) { //this is where we are  going to link to backend
        try {
            console.log(criteria);
            const queryParams = new URLSearchParams({ q: criteria })
            const queryString = queryParams.toString();
            console.log("query string is");
            console.log(queryString);

            const response = await this.axiosClient.get(`home/?${queryString}`);
            console.log(response);
            console.log(response.data);
            console.log(response.data.cars);


            return response.data.cars;
        } catch (error) {
            this.handleError(error, errorCallback)
        }

    }

    /**
     * Search for a car.
     * @param VIN A string containing search VIN to pass to the API.
     * @returns The car that matches the search criteria.
     */
    async getCar(vin, errorCallback) {
        try {
            const response = await this.axiosClient.get(`car/${vin}`);
            console.log("response is");
            console.log(response);
            return response.data.car;
        } catch (error) {
            this.handleError(error, errorCallback)}

    }
    /**
     * Delete a car.
     * @param VIN A string containing search VIN to pass to the API.
     * @returns The car that matches the search criteria.
     */
    async deleteCar(vin, errorCallback) {
        try {

            const token = await this.getTokenOrThrow("Only authenticated users can delete a car.");

            const response = await this.axiosClient.delete(`car/${vin}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.car;
        }
        catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Add a car.
     * @param vin, class, make, model, capacity, year, costPerDay to pass to the API.
     * @returns The new car.
     */
    async addCar(vin, classOfVehicle, make, model, capacity, year, costPerDay, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create a new car.");
            const response = await this.axiosClient.post(`car`, {
                vin: vin,
                classOfVehicle: classOfVehicle,
                make: make,
                model: model,
                capacity: capacity,
                year: year,
                costPerDay: costPerDay
                }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.car;
        }
        catch (error) {
           this.handleError(error, errorCallback);
        }
    }

        /**
         * Update a car.
         * @param vin, availability, costPerDay, errorCallback
         * @returns The new car.
         */

    async updateCar(vin, availability, costPerDay, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can update cars.");
            const response = await this.axiosClient.put(`car/${vin}`, {
                vin: vin,
                availability: availability,
                costPerDay: costPerDay
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.car;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}
