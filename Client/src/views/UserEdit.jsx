import React, { useState } from "react";

import { Link } from "react-router-dom";
function UserEdit() {
  const [image, setImage] = useState(
    "https://bootdey.com/img/Content/avatar/avatar7.png"
  );

  const handleImageChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const imageUrl = URL.createObjectURL(file);
      setImage(imageUrl);
    }
  };
  return (
    <div className="profile">
      <div className="container">
        <div className="main-body">
          {/* Breadcrumb */}
          <nav aria-label="breadcrumb" className="main-breadcrumb">
            <ol className="breadcrumb">
              <li className="breadcrumb-item">
                <a href="index.html">Home</a>
              </li>
              <li className="breadcrumb-item">
                <a href="javascript:void(0)">User</a>
              </li>
              <li className="breadcrumb-item active" aria-current="page">
                User Profile
              </li>
            </ol>
          </nav>
          {/* /Breadcrumb */}
          <div className="row gutters-sm">
            <div className="col-md-4 mb-3">
              <div className="card">
                <div className="card-body">
                  <div className="d-flex flex-column align-items-center text-center">
                    <label
                      htmlFor="profileImageInput"
                      style={{ cursor: "pointer" }}
                    >
                      <img
                        src={image}
                        alt="Profile"
                        className="rounded-circle"
                        width={150}
                        height={150}
                      />
                      <input
                        id="profileImageInput"
                        type="file"
                        accept="image/*"
                        onChange={handleImageChange}
                        style={{ display: "none" }}
                      />
                    </label>

                    <div className="mt-3">
                      <h4>Osura</h4>
                      <p className="text-secondary mb-1">
                        Full Stack Developer
                      </p>
                      <p className="text-muted font-size-sm">Malabe</p>
                      <p className="text-muted font-size-sm">Followers: 320</p>
                      <p className="text-muted font-size-sm">Following: 220</p>
                    </div>
                  </div>
                </div>
              </div>
              <div className="card mt-3">
                <ul className="list-group list-group-flush">
                  <li className="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                    <h6
                      className="mb-0"
                      style={{
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                        flex: 1,
                      }}
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width={24}
                        height={24}
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        strokeWidth={2}
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        className="feather feather-globe mr-2 icon-inline"
                      >
                        <circle cx={12} cy={12} r={10} />
                        <line x1={2} y1={12} x2={22} y2={12} />
                        <path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z" />
                      </svg>
                      Website
                    </h6>
                    <input
                      type="url"
                      className="form-control border-0 p-0"
                      placeholder="https://bootdey.com"
                      style={{
                        outline: "none",
                        backgroundColor: "transparent",
                      }}
                    />
                  </li>
                  <li className="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                    <h6
                      className="mb-0"
                      style={{
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                        flex: 1,
                      }}
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width={24}
                        height={24}
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        strokeWidth={2}
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        className="feather feather-github mr-2 icon-inline"
                      >
                        <path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22" />
                      </svg>
                      Github
                    </h6>
                    <input
                      type="text"
                      className="form-control border-0 p-0"
                      placeholder="bootdey"
                      style={{
                        outline: "none",
                        backgroundColor: "transparent",
                      }}
                    />
                  </li>
                  <li className="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                    <h6
                      className="mb-0"
                      style={{
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                        flex: 1,
                      }}
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width={24}
                        height={24}
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        strokeWidth={2}
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        className="feather feather-twitter mr-2 icon-inline text-info"
                      >
                        <path d="M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z" />
                      </svg>
                      Twitter
                    </h6>
                    <input
                      type="text"
                      className="form-control border-0 p-0"
                      placeholder="@bootdey"
                      style={{
                        outline: "none",
                        backgroundColor: "transparent",
                      }}
                    />
                  </li>
                  <li className="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                    {/* Instagram Icon and Text Wrapper */}
                    <div
                      style={{
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                        flex: 1,
                      }}
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width={24}
                        height={24}
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        strokeWidth={2}
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        className="feather feather-instagram mr-2 icon-inline text-danger"
                      >
                        <rect
                          x={2}
                          y={2}
                          width={20}
                          height={20}
                          rx={5}
                          ry={5}
                        />
                        <path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z" />
                        <line x1="17.5" y1="6.5" x2="17.51" y2="6.5" />
                      </svg>
                      Instagram
                    </div>

                    {/* Input Wrapper */}
                    <input
                      type="text"
                      className="form-control border-0 p-0"
                      placeholder="bootdey"
                      style={{
                        outline: "none",
                        backgroundColor: "transparent",
                      }}
                    />
                  </li>

                  <li className="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                    <h6
                      className="mb-0"
                      style={{
                        display: "flex",
                        alignItems: "center",
                        justifyContent: "center",
                        flex: 1,
                      }}
                    >
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width={24}
                        height={24}
                        viewBox="0 0 24 24"
                        fill="none"
                        stroke="currentColor"
                        strokeWidth={2}
                        strokeLinecap="round"
                        strokeLinejoin="round"
                        className="feather feather-facebook mr-2 icon-inline text-primary"
                      >
                        <path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z" />
                      </svg>
                      Facebook
                    </h6>
                    <input
                      type="text"
                      className="form-control border-0 p-0"
                      placeholder="bootdey"
                      style={{
                        outline: "none",
                        backgroundColor: "transparent",
                      }}
                    />
                  </li>
                </ul>
                <div className="card mt-3">
                  <button className="btn btn-primary">save</button>
                </div>
              </div>
            </div>
            <div className="col-md-8">
              <div className="card mb-3">
                <div className="card-body">
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Full Name</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      <input
                        type="text"
                        className="form-control border-0 p-0"
                        placeholder="Osura Samoda"
                        style={{
                          outline: "none",
                          backgroundColor: "transparent",
                        }}
                      />
                    </div>
                  </div>
                  <hr />
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Email</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      <input
                        type="email"
                        className="form-control border-0 p-0"
                        placeholder="fip@jukmuh.al"
                        style={{
                          outline: "none",
                          backgroundColor: "transparent",
                        }}
                      />
                    </div>
                  </div>
                  <hr />
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Phone</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      <input
                        type="tel"
                        className="form-control border-0 p-0"
                        placeholder="077 1234 5678"
                        style={{
                          outline: "none",
                          backgroundColor: "transparent",
                        }}
                      />
                    </div>
                  </div>
                  <hr />
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Mobile</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      <input
                        type="tel"
                        className="form-control border-0 p-0"
                        placeholder="0765380336"
                        style={{
                          outline: "none",
                          backgroundColor: "transparent",
                        }}
                      />
                    </div>
                  </div>
                  <hr />
                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Address</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      <input
                        type="text"
                        className="form-control border-0 p-0"
                        placeholder="Kiripedda, Malabe"
                        style={{
                          outline: "none",
                          backgroundColor: "transparent",
                        }}
                      />
                    </div>
                  </div>
                  <hr />

                  <div className="row">
                    <div className="col-sm-3">
                      <h6 className="mb-0">Achievements</h6>
                    </div>
                    <div className="col-sm-9 text-secondary">
                      <select
                        className="form-control border-0 p-0"
                        style={{
                          outline: "none",
                          backgroundColor: "transparent",
                          color: "#6c757d",
                        }}
                      >
                        <option value="">Select Achievements</option>
                        <option value="sf">100 Days Of Code</option>
                        <option value="ny">First Post</option>
                        <option value="la">5 Skill Shered </option>
                        <option value="la">Top Contributor </option>
                      </select>
                    </div>
                  </div>
                  <hr />

                  <div className="row">
                    <div className="col-sm-12">
                      <Link to="/user/user-edit" className="btn btn-info">
                        Save
                      </Link>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default UserEdit;
