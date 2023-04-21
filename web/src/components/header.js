import MusicPlaylistClient from '../api/musicPlaylistClient';
import BindingClass from "../util/bindingClass";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addHeaderToPage', 'createSiteTitle', 'createUserInfoForHeader',
            'createLoginButton', 'createLoginButton', 'createLogoutButton', 'createLinkBar'
        ];
        this.bindClassMethods(methodsToBind, this);

        this.client = new MusicPlaylistClient();
        console.log("client has been constructed, header constructor complete");
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const currentUser = await this.client.getIdentity();

        const siteTitle = this.createSiteTitle();
        const linkBarContents = this.createLinkBar();
        const userInfo = this.createUserInfoForHeader(currentUser);

        const header = document.getElementById('header');
        const linkBar = document.getElementById('link-bar');
        header.appendChild(siteTitle);
        header.appendChild(userInfo);
        linkBar.appendChild(linkBarContents);

    }


    createSiteTitle() {
        // Create the site title container div
        const siteTitle = document.createElement('div');
        siteTitle.classList.add('site-title');
        // Create the GIF image element #1
          const gifImage = document.createElement('img');
            gifImage.src = 'https://media.giphy.com/media/bKa7IXHj65ywvTwnbx/giphy.gif';
            gifImage.classList.add('gif-image');
            // Create the GIF image element #2
            const gifImage2 = document.createElement('img');
            gifImage2.src = 'https://media.giphy.com/media/bKa7IXHj65ywvTwnbx/giphy.gif';
            gifImage2.classList.add('gif-image');

            //style the GIF images
            gifImage.style.float = 'left';


         // Create the home button
        const homeButton = document.createElement('a');
        homeButton.classList.add('header_home');
        homeButton.href = 'index.html';
        homeButton.innerText = "Muddather\'s Car Rental Service";

        siteTitle.appendChild(gifImage);
        siteTitle.appendChild(homeButton);
        siteTitle.appendChild(gifImage2);

        return siteTitle;
    }

    createLinkBar() {
    const linkBar = document.createElement('p');
        linkBar.classList.add('button-group');

        const inventoryButton = document.createElement('a');
        inventoryButton.href = 'inventory.html';
        inventoryButton.innerText = 'Manage Inventory';
        const updateButton = document.createElement('a');
        updateButton.href = 'updateCar.html';
        updateButton.innerText = 'Update Existing Cars';
        const searchByVinButton = document.createElement('a');
        searchByVinButton.href = 'car.html';
        searchByVinButton.innerText = 'Search Cars by VIN';

        linkBar.appendChild(inventoryButton);
        linkBar.appendChild(updateButton);
        linkBar.appendChild(searchByVinButton);
        return linkBar;

    }

    createUserInfoForHeader(currentUser) {
        const userInfo = document.createElement('div');
        userInfo.classList.add('user');

        const childContent = currentUser //boolean
            ? this.createLogoutButton(currentUser) // if the childContent is the currentuser, create the logout button
            : this.createLoginButton(); // else create the login button

        userInfo.appendChild(childContent);

        return userInfo;
    }

    createLoginButton() {
        return this.createButton('Login', this.client.login);
    }

    createLogoutButton(currentUser) {
        return this.createButton(`Logout: ${currentUser.name}`, this.client.logout);
    }

    createButton(text, clickHandler) {
        const button = document.createElement('a');
        button.classList.add('button');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }
}
