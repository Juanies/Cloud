import PropTypes from 'prop-types';

const Link = (props) => {

    return (
        <div>
            <a href={props.link} title={props.title}>{props.msg}</a>
        </div>
    );
};

Link.propTypes = {
    link: PropTypes.string.isRequired, 
    title: PropTypes.string.isRequired,
    msg: PropTypes.string.isRequired
};

export default Link;
