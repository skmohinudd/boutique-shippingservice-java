CREATE TABLE IF NOT EXISTS shipping_processed_events(event_id UUID PRIMARY KEY,processed_at TIMESTAMPTZ NOT NULL);
CREATE TABLE IF NOT EXISTS shipping_outbox_events(id UUID PRIMARY KEY,aggregate_id UUID NOT NULL,event_type VARCHAR(100) NOT NULL,payload TEXT NOT NULL,created_at TIMESTAMPTZ NOT NULL,kafka_published_at TIMESTAMPTZ,rabbit_published_at TIMESTAMPTZ);
CREATE INDEX IF NOT EXISTS idx_shipping_outbox_pending ON shipping_outbox_events(created_at) WHERE kafka_published_at IS NULL OR rabbit_published_at IS NULL;
