import React, { useState } from 'react';
import { postData } from '../services/apiService';

const FormComponent = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await postData(formData);
    console.log(response);
  };

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-3">
        <label htmlFor="name" className="form-label">Name</label>
        <input 
          type="text" 
          className="form-control" 
          id="name" 
          value={formData.name} 
          onChange={(e) => setFormData({ ...formData, name: e.target.value })}
        />
      </div>
      <div className="mb-3">
        <label htmlFor="email" className="form-label">Email</label>
        <input 
          type="email" 
          className="form-control" 
          id="email" 
          value={formData.email} 
          onChange={(e) => setFormData({ ...formData, email: e.target.value })}
        />
      </div>
      <button type="submit" className="btn btn-primary">Submit</button>
    </form>
  );
};

export default FormComponent;
