import { useState } from 'react'

import './App.css'

import axios from 'axios';

function App() {
  const [file, setFile] = useState(null);
  const [id, setId] = useState('');
  const [originalFileName, setOriginalFileName] = useState('');

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
    // Obtener el nombre del archivo desde el input file
    setOriginalFileName(e.target.files[0].name);
  };

  const handleIdChange = (e) => {
    setId(e.target.value);
  };

  const handleUpload = () => {
    if (!file || !id || !originalFileName) {
      console.error('Debe seleccionar un archivo, proporcionar un ID y el nombre original.');
      return;
    }

    const formData = new FormData();
    formData.append('file', file);
    formData.append('id', id);
    formData.append('originalFileName', originalFileName);

    axios.post('http://localhost:8080/api/files/upload', formData)
      .then(response => {
        console.log('Response:', response.data);
        // Puedes realizar acciones adicionales después de la carga del archivo
      })
      .catch(error => {
        console.error('Error uploading file:', error);
      });
  };

  return (
    <div>
      <h1>Subir Archivo</h1>
      <input type="file" onChange={handleFileChange} />
      <br />
      <input type="text" placeholder="ID" value={id} onChange={handleIdChange} />
      <br />
      {/* Muestra el nombre original del archivo */}
      <input type="text" placeholder="Nombre Original del Archivo" value={originalFileName} readOnly />
      <br />
      <button onClick={handleUpload}>Subir Archivo</button>
    </div>
  );
}

export default App
