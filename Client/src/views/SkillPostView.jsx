import React, { useEffect, useState } from "react";
import axios from "axios";
import SlideBar from "../components/SlideBar";
import SkillPostData from "../data/SkillPostData";
import AllPost from "../components/skillPost/AllPost";

function SkillPostView() {
  const [post, setPosts] = useState([]);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get("http://localhost:8086/api/posts");
        setPosts(response.data);
      } catch (error) {
        console.error("Error fetching posts:", error); // Log error for fetching posts
      }
    };
    fetchPosts();
  }, []);

  const handledelete = async (id) => {
    try {
      const response = await axios.delete(
        `http://localhost:8086/api/posts/${id}`
      );
      console.log(response);
      setPosts((prevpost) => prevpost.filter((p) => p.id != id));
    } catch (error) {
      console.error("Error fetching posts:", error);
    }
  };

  return (
    <div>
      <section className="blog_area section-padding">
        <div className="container">
          <div className="row">
            <div className="col-lg-8 mb-5 mb-lg-0">
              {post.map((skillpost) => (
                <AllPost
                  key={skillpost.id}
                  skillpost={skillpost}
                  handledelete={handledelete}
                />
              ))}
            </div>
            <div className="col-lg-4">
              <SlideBar />
            </div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default SkillPostView;
