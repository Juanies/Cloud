import { useState } from 'react';
import './Title'
import PropTypes from 'prop-types';

const Title = (props) => {
    
    const [msg, setMsg] = useState(props.text);


    const welcomeMessages = [
        "Welcome to Cloud Harmony",
        "Enter the Cloud with a Smile",
        "Your Gateway to Seamless Cloud Experience",
        "Embrace Cloud Bliss with Easy Login",
        "Universal Access to Your Cloud Haven",
        "Step into Cloud Serenity",
        "Cloud Connection Made Simple",
        "Unlock Your Cloud Journey with a Warm Welcome",
        "Hello to Your Cloud Sanctuary",
        "Seamless Entry to Your Cloud World",
        "Embracing You in Cloud Comfort",
        "Your Passport to Cloud Tranquility",
        "Greetings, Cloud Traveler!",
        "Discover Ease with Cloud Arrival",
        "A Warm Welcome to Your Cloud Oasis",
        "Step Inside Your Cloud Universe",
        "Welcome Aboard Your Cloud Voyage",
        "Cloud Access, Tailored for You",
        "Greet Your Cloud Adventure with a Smile",
        "Your Cloud Welcome Starts Here",
        "Unlock the Doors to Your Cloud Realm",
        "Hello, Cloud Explorer!",
        "Your Cloud Journey Begins with Welcome",
        "Embracing You in Cloud Serenity",
        "Step into Your Cloud Sanctuary",
        "Welcome to Your Cloud Comfort Zone",
        "Your Cloud Home, Awaits with Open Arms",
        "Greetings, Cloud Voyager!",
        "Welcome to Seamless Cloud Delight",
        "Enter Your Cloud Oasis with a Friendly Hello",
    ];


    const changeMsg = () => {
        let aleatorio = Math.floor(Math.random() * 30);
        let h2 = document.getElementById('msg');

            if(h2.style.animationName == "closeTyping"){
                h2.style.animationName = "typing";
                h2.style.animationDuration = "5s";

            }else{
                h2.style.animationName = "closeTyping"; 

                h2.style.animationDuration = "5s";
                h2.onanimationend = ( )=> {
                    setMsg(welcomeMessages[aleatorio]);
                }

            }  
    };

    return(
        <h2 id='msg' className='h2' onAnimationEnd={changeMsg}>{msg}</h2>
    )
}

Title.propTypes = {
    text: PropTypes.string, 
};

export default Title