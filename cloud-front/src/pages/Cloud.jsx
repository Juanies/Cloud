import React, { useState, useEffect } from 'react';
import { useDropzone } from 'react-dropzone';

function Cloud() {
  const [files, setFiles] = useState([]);
  const [dropEnabled, setDropEnabled] = useState(true);

  const handleFileUpload = async (files) => {
    const formData = new FormData();
    files.forEach((file) => {
      formData.append('files', file, file.name); // Usar 'files' como el nombre de los archivos
    });

    try {
      const response = await fetch("http://localhost:8080/api/files/upload", {
        method: "POST",
        credentials: 'include',
        body: formData,
      });

      if (response.ok) {
        fetchFiles();
      } else {
        console.error('Error al subir archivos');
      }
    } catch (error) {
      console.error('Error al subir archivos:', error);
    } finally {
      setDropEnabled(true);
    }
  };

  const { getRootProps, getInputProps } = useDropzone({
    onDrop: (acceptedFiles) => {
      if (dropEnabled) {
        handleFileUpload(acceptedFiles);
      }
    }
  });

  const fetchFiles = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/files/getfiles", {
        method: "GET",
        credentials: 'include'
      });
      if (response.ok) {
        const data = await response.json();
        setFiles(data);
      } else {
        console.error('Error al obtener archivos');
      }
    } catch (error) {
      console.error('Error al obtener archivos:', error);
    }
  };

  useEffect(() => {
    fetchFiles();
  }, []);

  return (
    <div className='h-[100dvh] w-[100dvw]'>
      <div {...getRootProps()} style={{ border: '1px dashed black', padding: '20px', textAlign: 'center' }}>
        <input {...getInputProps()} />
        <p>Arrastra y suelta los archivos aquí, o haz clic para seleccionar archivos</p>
      </div>
      <div className='p-4'>
        <h2 className="text-2xl">Archivos:</h2>
        <ul className='flex flex-col overflow-y-scroll h-[83dvh]'>
          {files.map((file, index) => (
            <li key={index} className='flex bg-[#262626] p-2 my-2 text-white'>
              <p>{file.filename}</p>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default Cloud;
