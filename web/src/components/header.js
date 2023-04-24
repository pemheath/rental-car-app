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
            'createLoginButton', 'createLoginButton', 'createLogoutButton'
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
        const userInfo = this.createUserInfoForHeader(currentUser);

        const header = document.getElementById('header');
        header.appendChild(siteTitle);
        console.log("added header");
        header.appendChild(userInfo);
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
            // Append the GIF image to the site title container


         // Create the home button
        const homeButton = document.createElement('a');
        homeButton.classList.add('header_home');
        homeButton.href = 'index.html';
        homeButton.innerText = "Muddather\'s Car Rental Service";

        // appended home button to site title
        siteTitle.appendChild(homeButton);
        siteTitle.appendChild(gifImage);
                    siteTitle.appendChild(gifImage2);
        return siteTitle;
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
