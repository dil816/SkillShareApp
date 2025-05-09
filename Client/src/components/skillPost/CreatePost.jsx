import React, { useState, useRef, useEffect } from "react";
import author from "../../assets/clients/img/blog/author.png";
import singleblog1 from "../../assets/clients/img/blog/single_blog_1.png";
import SlideBar from "../SlideBar";
import axios from "axios";
import { useNavigate, useParams } from "react-router-dom";

function CreatePost() {
  const { id } = useParams();
  const navigate = useNavigate();
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [tags, setTags] = useState("");
  const [media, setMedia] = useState(null);
  const [mediaType, setMediaType] = useState("image");
  const [mediaForUpload, setMediaForUpload] = useState(null);
  const fileInputRef = useRef(null); // to programmatically trigger file input

  useEffect(() => {
    console.log(id);
    const fetchPosts = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8086/api/posts/${id}`
        );
        console.log(response);
        setTitle(response.data.contentTitle);
        setDescription(response.data.postDescription);
        setTags(response.data.tags);
        if (response.data.imageUrl) {
          setMedia(response.data.imageUrl);
        } else {
          setMedia(response.data.videoUrl);
        }
      } catch (error) {
        console.error("Error fetching posts:", error); // Log error for fetching posts
      }
    };
    if (id) {
      fetchPosts();
    }
  }, [id]);

  const handleMediaChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      const type = file.type.startsWith("video") ? "video" : "image";
      setMediaType(type);
      setMedia(URL.createObjectURL(file));
      if (file.type.startsWith("video") || file.type.startsWith("image")) {
        setMediaForUpload(file);
      } else {
        alert("please Add Video or Image");
      }
    }
  };

  const triggerFileInput = () => {
    fileInputRef.current.click(); // simulate click on hidden file input
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    var response;
    const formData = new FormData();
    formData.append("Tags", tags);
    formData.append("ContentTitle", title);
    formData.append("PostDescription", description);
    formData.append("MediaFile", mediaForUpload);

    try {
      console.log(formData);
      if (id) {
        response = await axios.put(
          `http://localhost:8086/api/posts/${id}`,
          formData,
          {
            headers: { "Content-Type": "multipart/form-data" },
          }
        );
        console.log(response.data);
        alert("Post updated successfully!");
        navigate("/user/skill-post-view");
      } else {
        response = await axios.post(
          "http://localhost:8086/api/posts/create",
          formData,
          {
            headers: { "Content-Type": "multipart/form-data" },
          }
        );
        console.log(response.data);
        alert("Post created successfully!");
        navigate("/user/skill-post-view");
      }
      //window.location.href = "/myAllPost";
    } catch (error) {
      console.error(error);
      alert("Failed to create post.");
      window.location.reload();
    }
  };

  return (
    <div>
      <div className="d-flex justify-content-center">
        <h2 className="mb-4">Create Post</h2>
      </div>

      <div className="single-post">
        {media && (
          <div
            className="feature-img mb-3"
            onClick={triggerFileInput}
            style={{ cursor: "pointer" }}
          >
            {mediaType === "image" ? (
              <img className="img-fluid" src={media} alt="Uploaded" />
            ) : (
              <video className="img-fluid" controls>
                <source src={media} type="video/mp4" />
                Your browser does not support the video tag.
              </video>
            )}
          </div>
        )}

        {/* Hidden input field */}
        <form onSubmit={handleSubmit}>
          <input
            type="file"
            accept="image/*,video/*"
            ref={fileInputRef}
            onChange={handleMediaChange}
          />

          <div className="blog_details">
            <input
              type="text"
              className="form-control mb-3"
              placeholder="Add Your Title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />

            <textarea
              className="form-control mb-3"
              rows={3}
              placeholder="Add Your First Paragraph"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            ></textarea>

            <textarea
              className="form-control mb-3"
              rows={1}
              placeholder="Add Your Tags"
              value={tags}
              onChange={(e) => setTags(e.target.value)}
            ></textarea>
          </div>
          <div className="d-flex justify-content-center">
            <button className="btn btn-info mt-3">Publish</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default CreatePost;
