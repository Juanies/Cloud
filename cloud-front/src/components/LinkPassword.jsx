import PropTypes from 'prop-types';
import './LinkPassword.css';

const LinkPassword = (props) => {

    return (
        <div>
            <a href={props.link} title={props.title}>{props.msg}</a>
        </div>
    );
};

LinkPassword.propTypes = {
    link: PropTypes.string.isRequired, 
    title: PropTypes.string.isRequired,
    msg: PropTypes.string.isRequired
};

export default LinkPassword;
