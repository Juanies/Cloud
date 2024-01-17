import { useState } from 'react'

import PropTypes from 'prop-types';
import './File.css';
import Favorite from '../assets/star-regular.svg';



const Button = (props) => {

    const [clicked, setClicked] = useState(false);


    const handleClick = (event) => {
        const element = event.target; // Assuming you want to target the element that was clicked

        if (clicked) {
            element.style.background = "transparent";
            setClicked(false);
        }else{
            element.style.background = "red";
            setClicked(true);

        }

    };

        return (
            <div className='file'>
                <img src={props.urlimg} alt="" />
                <p className='namefile'> {props.namefile} </p>
                <p className='datefile'>{props.datefile}</p>
                <img  onClick={handleClick} className='star' src={Favorite} alt="" />
            </div>
        );
    };

Button.propTypes = {
    urlimg: PropTypes.string.isRequired, 
    namefile: PropTypes.string.isRequired,
    datefile: PropTypes.string.isRequired
};

export default Button;
