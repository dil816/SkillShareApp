import React, { useState, useEffect } from "react";

const CommentCreate = () => {
  const [comment, setComment] = useState("");
  const [author, setAuthor] = useState("");
  const [createdAt, setCreatedAt] = useState("");

  // Set the current Sri Lankan time on mount
  useEffect(() => {
    const getSLTime = () => {
      const date = new Date();
      const options = {
        timeZone: "Asia/Colombo",
        hour12: false,
      };
      return date.toLocaleString("sv-SE", options).replace("T", " ");
    };
    setCreatedAt(getSLTime());
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const feedbackData = {
      comment,
      author,
      createdAt,
      likeCount: "0", // you can start with 0 likes or adjust as needed
    };

    try {
      const response = await fetch("http://localhost:8086/api/feedback/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(feedbackData),
      });

      if (response.ok) {
        alert("Comment submitted successfully!");
        setComment("");
        setAuthor("");
        // Optionally update createdAt again
        const newDate = new Date().toLocaleString("sv-SE", {
          timeZone: "Asia/Colombo",
          hour12: false,
        });
        setCreatedAt(newDate.replace("T", " "));
      } else {
        alert("Failed to submit comment.");
      }
    } catch (error) {
      console.error("Error submitting comment:", error);
      alert("Error submitting comment.");
    }
  };

  return (
    <form className="form-contact comment_form" onSubmit={handleSubmit}>
      <div className="row">
        <div className="col-12">
          <div className="form-group">
            <textarea
              className="form-control w-100"
              name="comment"
              id="comment"
              cols={30}
              rows={9}
              placeholder="Write Comment"
              value={comment}
              onChange={(e) => setComment(e.target.value)}
            />
          </div>
        </div>
        <div className="col-sm-6">
          <div className="form-group">
            <input
              className="form-control"
              name="author"
              id="author"
              type="text"
              placeholder="Name"
              value={author}
              onChange={(e) => setAuthor(e.target.value)}
            />
          </div>
        </div>
        <div className="col-sm-6">
          <div className="form-group">
            <input
              className="form-control"
              name="createdAt"
              id="createdAt"
              type="text"
              placeholder="Created At"
              value={createdAt}
              readOnly
            />
          </div>
        </div>
      </div>
      <div className="form-group">
        <button type="submit" className="button button-contactForm btn_1 boxed-btn">
          Send Comment
        </button>
      </div>
    </form>
  );
};

export default CommentCreate;