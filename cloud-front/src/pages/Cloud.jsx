import { useEffect, useState } from 'react';
import './Cloud.css'; // Ajusta el nombre del archivo CSS según sea necesario

function Cloud() {
    const [files, setFiles] = useState([]);

    useEffect(() => {
        // Lógica para obtener los archivos desde el backend
        const fetchData = async () => {
            try {
                const response = await fetch('http://localhost:8080/api/files/getfiles', {
                    method: 'GET',
                    headers: {
                        // Aquí puedes agregar headers necesarios como las cookies de autenticación
                        'Content-Type': 'application/json',
                    },
                });

                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }

                const data = await response.json();
                setFiles(data); // Actualiza el estado con los archivos obtenidos
            } catch (error) {
                console.error('Error:', error.message);
            }
        };

        fetchData(); // Llama a la función para obtener los archivos al cargar el componente
    }, []); // El segundo parámetro del useEffect asegura que se ejecute solo una vez al montar el componente

    return (
        <div className='cloud-container'>
            <h1>Cloud Files</h1>
            <ul>
                {files.map((file, index) => (
                    <li key={index}>{file.name}</li>
                    // Ajusta esta parte según la estructura de tus objetos de archivo
                ))}
            </ul>
        </div>
    );
}

export default Cloud;
