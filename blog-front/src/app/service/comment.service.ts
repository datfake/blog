import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private apiService: ApiService) { }

  public GetComments(id: string): Observable<Comment[]> {
    return this.apiService.Get(environment.api.entries+"/comnents" + id).pipe(
      map(json => {
        return json.map(post => new Comment(post));
      })
    );
  }

  public GetComment(id: string): Observable<Comment> {
    return this.apiService.Get(environment.api.entries + "/posts/" + id).pipe(
      map(json => {
        return new Comment(json);
      })
    );
  }

  public CreateComment(post: Comment): Observable<any> {
    return this.apiService.Post(environment.api.entries + "/posts", post);
  }

  public deleteCommnet(id) {
    return this.apiService.Delete(environment.api.entries + "/posts/" + id);
  }
}
