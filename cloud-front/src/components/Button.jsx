import { useState } from 'react'

import PropTypes from 'prop-types';
import './Button.css';


const Button = (props) => {

        return (
            <div className='button-container'>
                <button type={props.type}>{props.text}</button>
            </div>
        );
    };

Button.propTypes = {
    text: PropTypes.string.isRequired, 
    type: PropTypes.string.isRequired,
};

export default Button;
