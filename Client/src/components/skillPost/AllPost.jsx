import axios from "axios";
import React from "react";
import { Link } from "react-router-dom";

function AllPost({ skillpost, handledelete }) {
  return (
    <div>
      <div>
        <div className="blog_left_sidebar">
          <article className="blog_item">
            <div className="blog_item_img">
              {skillpost.videoUrl && (
                <video
                  className="card-img rounded-0"
                  src={skillpost.videoUrl}
                  controls
                  style={{
                    width: "770px",
                    height: "370px",
                    marginBottom: "10px",
                  }}
                />
              )}
              {skillpost.imageUrl && (
                <img
                  className="card-img rounded-0"
                  src={skillpost.imageUrl}
                  alt=""
                  style={{ width: "770px", height: "370px" }}
                />
              )}
            </div>
            <div className="blog_details">
              <a className="d-inline-block" href="single-blog.html">
                <h2>{skillpost.contentTitle}</h2>
              </a>
              <p>{skillpost.postDescription}</p>
              <ul className="blog-info-link">
                <li>
                  <a href="#">
                    <i className="fa fa-user" /> {skillpost.category}
                  </a>
                </li>
                <li>
                  <a href="#">
                    <i className="fa fa-comments" /> {skillpost.commentCount}
                  </a>
                </li>
                <li>
                  <a href="#">
                    <Link to={`/user/skillPost/${skillpost.id}`}>update</Link>
                  </a>
                </li>
                <li>
                  <a onClick={() => handledelete(skillpost.id)}>delete</a>
                </li>
              </ul>
            </div>
          </article>
        </div>
      </div>
    </div>
  );
}

export default AllPost;
