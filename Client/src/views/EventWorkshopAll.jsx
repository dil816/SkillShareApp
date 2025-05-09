<<<<<<< HEAD
import React, { useState, useEffect } from "react";
import { Card, Button, Container, Row, Col, Alert } from "react-bootstrap";
import { Link } from "react-router-dom";
import eventService from "../services/eventService";
=======
import React, { useState, useEffect } from 'react';
import { Card, Button, Container, Row, Col, Alert } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import eventService from '../services/eventService';
>>>>>>> 2339839ba8da6e8c3aa88118e461f8af08164a9a

function EventWorkshopAll() {
  const [events, setEvents] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Fetch events from the API
  useEffect(() => {
    const fetchEvents = async () => {
      try {
        setLoading(true);
        const data = await eventService.getAllEvents();
        setEvents(data);
        setLoading(false);
      } catch (err) {
<<<<<<< HEAD
        setError("Failed to load events. Please try again later.");
=======
        setError('Failed to load events. Please try again later.');
>>>>>>> 2339839ba8da6e8c3aa88118e461f8af08164a9a
        setLoading(false);
      }
    };

    fetchEvents();
  }, []);

  const formatDate = (dateString) => {
<<<<<<< HEAD
    if (!dateString) return "Date not available";
    const options = { year: "numeric", month: "long", day: "numeric" };
    return new Date(dateString).toLocaleDateString(undefined, options);
  };

  if (loading)
    return (
      <Container className="py-5">
        <div className="text-center">Loading events...</div>
      </Container>
    );

  if (error)
    return (
      <Container className="py-5">
        <Alert variant="danger">{error}</Alert>
      </Container>
    );
=======
    if (!dateString) return 'Date not available';
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString(undefined, options);
  };

  if (loading) return (
    <Container className="py-5">
      <div className="text-center">Loading events...</div>
    </Container>
  );

  if (error) return (
    <Container className="py-5">
      <Alert variant="danger">{error}</Alert>
    </Container>
  );
>>>>>>> 2339839ba8da6e8c3aa88118e461f8af08164a9a

  return (
    <Container className="py-5">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2>Event Workshops</h2>
      </div>
<<<<<<< HEAD

=======
      
>>>>>>> 2339839ba8da6e8c3aa88118e461f8af08164a9a
      {events.length === 0 ? (
        <Alert variant="info">No events currently available.</Alert>
      ) : (
        <Row>
<<<<<<< HEAD
          {events.map((event) => (
=======
          {events.map(event => (
>>>>>>> 2339839ba8da6e8c3aa88118e461f8af08164a9a
            <Col md={6} lg={4} className="mb-4" key={event.id}>
              <Card className="h-100 shadow-sm">
                <Card.Body>
                  <Card.Title>{event.eventName}</Card.Title>
                  <Card.Text as="div">
                    <div className="mb-2">
                      <strong>Organizer:</strong> {event.organizer}
                    </div>
                    <div className="mb-2">
                      <strong>Date:</strong> {formatDate(event.eventDate)}
                    </div>
                    <div className="mb-2">
                      <strong>Location:</strong> {event.location}
                    </div>
                    <div className="text-truncate mt-3">
                      {event.description}
                    </div>
                  </Card.Text>
                </Card.Body>
<<<<<<< HEAD
                <Card.Footer className="bg-white"></Card.Footer>
=======
                <Card.Footer className="bg-white">
                 
                </Card.Footer>
>>>>>>> 2339839ba8da6e8c3aa88118e461f8af08164a9a
              </Card>
            </Col>
          ))}
        </Row>
      )}
    </Container>
  );
}
<<<<<<< HEAD

=======
 
>>>>>>> 2339839ba8da6e8c3aa88118e461f8af08164a9a
export default EventWorkshopAll;
