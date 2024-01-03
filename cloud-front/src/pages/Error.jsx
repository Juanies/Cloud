import "./Error"
import "./Error.css"
function Error (){

    return( 
        <div>
            <h2>Error 404</h2>
            <p>Page not found</p>
            <p>{window.location.href}</p>
        </div>
    )

}

export default Error
