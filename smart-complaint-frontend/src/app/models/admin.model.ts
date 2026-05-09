export interface ComplaintResponse {
  id: number;
  title: string;
  description: string;
  status: 'OPEN' | 'IN_PROGRESS' | 'RESOLVED';
  createdAt: string;
  updatedAt: string;

  userId?: number;
  userName?: string;
  userEmail?: string;
}

export interface DashboardStatsResponse {
  totalComplaints: number;
  openComplaints: number;
  inProgressComplaints: number;
  resolvedComplaints: number;
}

export interface StatusUpdateRequest {
  status: 'OPEN' | 'IN_PROGRESS' | 'RESOLVED';
}