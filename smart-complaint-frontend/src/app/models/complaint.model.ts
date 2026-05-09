export interface ComplaintCreateRequest {
  title: string;
  description: string;
}

export interface ComplaintResponse {
  id: number;
  title: string;
  description: string;
  status: 'OPEN' | 'IN_PROGRESS' | 'RESOLVED';
  createdAt: string;
  updatedAt: string;
  userName?: string;
  userEmail?: string;
}