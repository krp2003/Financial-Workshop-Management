import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { AuthService } from './auth.service';
 
@Injectable({
  providedIn: 'root'
})
export class HttpService {
 
  public serverName = environment.apiUrl;
 
  constructor(private http: HttpClient, private authService: AuthService) {}
 
  // Helper: Add auth token in headers
  private getAuthHeaders(): HttpHeaders {
    let token = this.authService.getToken();
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: token ? `Bearer ${token}` : ''
    });
  }
 
  // ----------------- USER -----------------
  registerUser(details: any): Observable<any> {
    return this.http.post(`${this.serverName}/api/user/register`, details);
  }
 
  login(details: any): Observable<any> {
    return this.http.post(`${this.serverName}/api/user/login`, details);
  }
 
  // ----------------- INSTITUTION -----------------
  createEvent(details: any): Observable<any> {
    return this.http.post(`${this.serverName}/api/institution/event`, details, {
      headers: this.getAuthHeaders()
    });
  }
 
  updateEvent(eventId: any, details: any): Observable<any> {
    return this.http.put(`${this.serverName}/api/institution/event/${eventId}`, details, {
      headers: this.getAuthHeaders()
    });
  }
 
  getEventByInstitutionId(institutionId: any): Observable<any> {
    return this.http.get(`${this.serverName}/api/institution/events?institutionId=${institutionId}`, {
      headers: this.getAuthHeaders()
    });
  }
 
  addResource(details: any): Observable<any> {
    return this.http.post(`${this.serverName}/api/institution/event/${details.eventId}/resource`, details, {
      headers: this.getAuthHeaders()
    });
  }
 
  GetAllProfessionals(): Observable<any> {
    return this.http.get(`${this.serverName}/api/institution/event/professionals`, {
      headers: this.getAuthHeaders()
    });
  }
 
  assignProfessionals(eventId: any, userId: any): Observable<any> {
    return this.http.post(`${this.serverName}/api/institution/event/${eventId}/professional?userId=${userId}`, {}, {
      headers: this.getAuthHeaders()
    });
  }
 
  // ----------------- PROFESSIONAL -----------------
  getEventByProfessional(userId: any): Observable<any> {
    return this.http.get(`${this.serverName}/api/professional/events?userId=${userId}`, {
      headers: this.getAuthHeaders()
    });
  }
 
  UpdateEventStatus(eventId: any, status: any): Observable<any> {
    return this.http.put(`${this.serverName}/api/professional/event/${eventId}/status?status=${status}`, {}, {
      headers: this.getAuthHeaders()
    });
  }
 
  AddFeedback(eventId: any, userId: any, details: any): Observable<any> {
    return this.http.post(`${this.serverName}/api/professional/event/${eventId}/feedback?userId=${userId}`, details, {
      headers: this.getAuthHeaders()
    });
  }
 
  // ----------------- PARTICIPANT -----------------
  GetAllevents(): Observable<any> {
    return this.http.get(`${this.serverName}/api/finance/events`, {
      headers: this.getAuthHeaders()
    });
  }
 
  viewAllEvents(): Observable<any> {
    return this.http.get(`${this.serverName}/api/participant/events`, {
      headers: this.getAuthHeaders()
    });
  }
 
  EnrollParticipant(eventId: any, userId: any): Observable<any> {
    return this.http.post(`${this.serverName}/api/participant/event/${eventId}/enroll?userId=${userId}`, {}, {
      headers: this.getAuthHeaders()
    });
  }
 
  viewEventStatus(eventId: any): Observable<any> {
    return this.http.get(`${this.serverName}/api/participant/event/${eventId}/status`, {
      headers: this.getAuthHeaders()
    });
  }
 
  addFeedbackByParticipants(eventId: any, userId: any, details: any): Observable<any> {
    return this.http.post(`${this.serverName}/api/participant/event/${eventId}/feedback?userId=${userId}`, details, {
      headers: this.getAuthHeaders()
    });
  }
}
 